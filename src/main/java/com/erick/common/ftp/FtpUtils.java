package com.erick.common.ftp;

import com.erick.common.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * ftp工具类
 * @author erick
 * @version 1.0
 * @since 2019-11-29
 */
@Slf4j
public class FtpUtils {

    /**
    * 服务器ip
    */
    private String ip;

    /**
    * 端口号
    */
    private int port;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 密码
    */
    private String password;

    /**
    * FTP客户端
    */
    private FTPClient ftpClient;

    public FtpUtils(String ip , int port , String userName , String password){
        this.ip = ip;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    /**
    * 初始化客户端
    * @author  erick
    * @since 2020/4/11 2:34 下午
    * @param encode 字符编码
    *
    */
    private void initFtpClient(String encode) throws Exception{
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding(encode);
        try {
            log.info("连接FTP服务器ip：{} , 端口：{}" , ip , port);
            ftpClient.connect(ip , port);
            ftpClient.login(userName , password);
            int replyCode = ftpClient.getReplyCode();
            if (FTPReply.isPositiveCompletion(replyCode)){
                log.info("连接服务器成功，应答码是：{}" , replyCode);
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                return;
            }
            log.info("连接服务器失败，应答码是：{}" , replyCode);
        }catch (Exception e){
            e.printStackTrace();
            log.error("连接FTP服务器发生异常，e:{}" , e);
            throw e;
        }
    }
    /**
    * 上传文件
    * @author  erick
    * @since 2020/4/11 2:49 下午
    * @param encoding 设置编码
    * @param filePath 上传文件路径
    * @param fileName 文件名称
    * @param inputStream 待上传文件的输入流
    * @return 是否上传成功 true：上传成功，false ：上传失败
    *
    */
    public boolean uploadFile(String encoding , String filePath , String fileName , InputStream inputStream) throws Exception{

        if (ObjectUtils.isEmpty(inputStream)){
            return false;
        }
        if (StringUtils.isEmpty(encoding)){
            encoding = Constant.UTF_8;
        }
        if (StringUtils.isEmpty(filePath)){
            return false;
        }
        if (StringUtils.isEmpty(fileName)){
            return false;
        }
        try {
            //初始化Client
            initFtpClient(encoding);
            log.info("开始上传文件，filePath：{} , fileName：{}" , filePath , fileName);
            //判断目标路径是否存在，若存在则上传，若不存在则新建目录
            if (changeAndCreateDirectory(filePath)){
                ftpClient.storeFile(fileName , inputStream);
                log.info("文件上传成功");
            }
            log.info("上传文件结束.....................");
        }catch (Exception e){
            log.error("上传文件发生异常：e:{}" , e);
            throw e;
        }finally {
            //关闭输入流
            if (ObjectUtils.isNotEmpty(inputStream)){
                inputStream.close();
            }
            if (ObjectUtils.isNotEmpty(ftpClient) || ftpClient.isConnected()){
                if (ftpClient.logout()){
                    log.info("退出ftp服务器");
                }
                ftpClient.disconnect();
                log.info("关闭ftp连接。。。。。。");
            }
        }
        return true;
    }

    /**
    * 上传文件
    * @author  erick
    * @since 2020/4/13 10:25 上午
    * @param encoding 编码
    * @param fileName 文件名称
    * @param filePath 目标路径
    * @param sourceFilePath 上传文件路径
    * @return 上传结果
    * @exception Exception
    */
    public boolean uploadFile(String encoding , String filePath , String fileName , String sourceFilePath) throws Exception{
        if (StringUtils.isEmpty(sourceFilePath)){
            return false;
        }
        InputStream inputStream = new  FileInputStream(new File(sourceFilePath));
        return uploadFile(encoding , filePath , fileName , inputStream);
    }

    /**
    * 切换到制定目录
    * @author  erick
    * @since 2020/4/16 3:53 下午
    * @param filePath 目标文件路径
    * @return 是否切换成功
    *
    */
    private boolean changeAndCreateDirectory(String filePath) throws Exception{
        //切换目录成功则直接返回结果
        if (ftpClient.changeWorkingDirectory(filePath)){
            return true;
        }
        //切换目录失败，则说明目镜不存在，需要创建目录
        //获取第一层目录
        int startNum = Constant.NUM_INT_0;
        int endNum = Constant.NUM_INT_0;
        //若以"/"开始，则+1跳过/
        if (filePath.startsWith(Constant.CHAR_SLASH)){
            startNum = startNum + 1;
        }
        //从startNum开始获取下一个"/"的位置，若为结束，获取第一层目录
        endNum = filePath.indexOf(Constant.CHAR_SLASH , startNum);
        //循环创建目录
        while (true){
            String subDirectory = new String(filePath.substring(startNum , endNum).getBytes(Constant.GBK) , Constant.ISO_8859_1);
            //判断目录是否存在
            if (existFile(subDirectory)){
                ftpClient.changeWorkingDirectory(subDirectory);
            }else {
                //不存在则创建
                if (ftpClient.makeDirectory(subDirectory)){
                    ftpClient.changeWorkingDirectory(subDirectory);
                }
            }
            startNum = endNum + 1;
            endNum = filePath.indexOf(Constant.CHAR_SLASH , startNum);
            if (endNum <= startNum){
                break;
            }
        }
        return true;
    }

    /**
    * 判断文件是否存在
    * @author  erick
    * @since 2020/4/17 2:28 下午
    * @param filePath 文件路径
    * @return 是否存在 true：存在，false：不存在
    * @exception Exception
    *
    */
    private boolean existFile(String filePath) throws Exception{
        FTPFile[] ftpFile = ftpClient.listFiles(filePath);
        if (ftpFile.length > 0){
            return true;
        }
        return false;
    }

    /**
    * 下载文件
    * @author  erick
    * @since 2020/4/17 3:56 下午
    * @param encoding 字符编码
    * @param filePath ftp服务器文件路径
    * @param fileName 文件名称
    * @param localFilePath 下载到本地路径
    * @return 下载是否成功
    * @exception Exception
    *
    */
    public boolean downloadFile(String encoding , String filePath , String fileName , String localFilePath) throws Exception{
        boolean downloadFlag = false;
        OutputStream outputStream = null;
        if (StringUtils.isEmpty(encoding)){
            encoding = Constant.UTF_8;
        }
        if (StringUtils.isEmpty(filePath)){
            log.info("服务器文件路径不能为空");
            return false;
        }
        if (StringUtils.isEmpty(fileName)){
            log.info("文件名称不能为空");
            return false;
        }
        if (StringUtils.isEmpty(localFilePath)){
            log.info("本地路径不能为空");
            return false;
        }
        try {
            //初始化
            initFtpClient(encoding);
            //切换到目标路径
            if (ftpClient.changeWorkingDirectory(filePath)){
                FTPFile[] ftpFiles = ftpClient.listFiles();
                //查找符合要求文件
                for (FTPFile ftpFile : ftpFiles) {
                    if (fileName.equalsIgnoreCase(ftpFile.getName())){
                        File localFile = new File(localFilePath + Constant.CHAR_SLASH + ftpFile.getName());
                        outputStream = new FileOutputStream(localFile);
                        ftpClient.retrieveFile(ftpFile.getName() , outputStream);
                        downloadFlag = true;
                        log.info("文件下载完成........");
                        break;
                    }
                }
            }
            return downloadFlag;
        }catch (Exception e){
            log.error("下载文件发生异常，e:{}" , e);
            throw e;
        }finally {
            if (ObjectUtils.isNotEmpty(outputStream)){
                outputStream.close();
            }
            if (ObjectUtils.isNotEmpty(ftpClient) || ftpClient.isConnected()){
                if (ftpClient.logout()){
                    log.info("退出ftp服务器");
                }
                ftpClient.disconnect();
                log.info("关闭ftp连接。。。。。。");
            }
        }
    }
}
