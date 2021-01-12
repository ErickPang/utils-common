package com.erick.common.http;


import com.erick.common.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 * @author pangshaoshan
 * @version : 1.0
 * @since  :2019-10-24
 */
@Slf4j
public class HttpUtils {

    /**
     * http请求
     * @param sendMsg 发送的数据
     * @param accessUrl 访问的地址
     * @param connectTimeout 连接超时时间
     * @param readTimeout 读取超时时间
     * @return 返回接收到的数据
     * @author : pangshaoshan
     * @since  : 2019-10-24
     */
    public static String httpMethodByStream(String sendMsg , String accessUrl , String requestMthod ,
                                            int connectTimeout , int readTimeout){
        if (log.isDebugEnabled()){
            log.debug("http请求httpMethodByStream----->开始，sendMsg:{}" , sendMsg);
        }
        URL url=null;
        OutputStream outputStream = null;
        ObjectOutputStream objOutputStream = null;
        InputStream inputStream = null;
        ObjectInputStream objInputStream = null;
        HttpURLConnection httpUrlConnection=null;
        String resultStr = "";
        try {
            url = new URL(accessUrl);
            //获取连接
            httpUrlConnection=(HttpURLConnection) url.openConnection();
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestMethod(requestMthod);
            httpUrlConnection.setAllowUserInteraction(true);
            httpUrlConnection.setConnectTimeout(connectTimeout);
            httpUrlConnection.setReadTimeout(readTimeout);
            outputStream = httpUrlConnection.getOutputStream();
            objOutputStream = new ObjectOutputStream(outputStream);
            objOutputStream.writeObject(sendMsg);
            objOutputStream.flush();
            objOutputStream.close();
            outputStream.flush();
            outputStream.close();
            inputStream = httpUrlConnection.getInputStream();
            objInputStream = new ObjectInputStream(inputStream);
            Object object = objInputStream.readObject();
            resultStr = object.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ObjectUtils.isNotEmpty(inputStream)){
                    inputStream.close();
                }
                if (ObjectUtils.isNotEmpty(objInputStream)){
                    objInputStream.close();
                }
                if (ObjectUtils.isNotEmpty(outputStream)){
                    outputStream.close();
                }
                if (ObjectUtils.isNotEmpty(objOutputStream)){
                    objOutputStream.close();
                }
                if (ObjectUtils.isNotEmpty(httpUrlConnection)){
                    httpUrlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (log.isDebugEnabled()){
            log.debug("http请求httpMethodByStream----->结束，resultStr:{}" , resultStr);
        }
        return resultStr;
    }

    /**
     * 采用httpclient方式以get方式进行请求
     * @author pangshaoshan
     * @since 2019-11-18
     * @param accessUrl 请求地址
     * @param jsonStr 发送数据格式为json
     * @param connectTimeout 连接超时时间
     * @param connectionRequestTimeout 连接请求超时时间
     * @param socketTimeout 读取超时时间
     * @return 返回结果
     */
    public static String sendMsgByPostMethod(String accessUrl , String jsonStr , int connectTimeout
            , int connectionRequestTimeout , int socketTimeout) throws Exception{
        if (StringUtils.isEmpty(jsonStr)){
            throw new Exception("传输数据不能为空");
        }
        StringEntity stringEntity = new StringEntity(jsonStr , Consts.UTF_8);
        stringEntity.setContentEncoding(Constant.UTF_8);
        stringEntity.setContentType("application/json");
        return httpPostRequest(accessUrl,stringEntity,connectTimeout,connectionRequestTimeout,socketTimeout);
    }

    /**
     * Post请求参数为map类型
     * @param accessUrl  请求地址
     * @param paraMap  请求参数，key：参数名称，value：参数值
     * @param connectTimeout 连接超时时间
     * @param connectionRequestTimeout 连接请求超时时间
     * @param socketTimeout 读取超时时间
     * @return 对方请求结果
     */
    public static String sendMapMsgByPost(String accessUrl , Map<String , String> paraMap
            , int connectTimeout, int connectionRequestTimeout , int socketTimeout) throws Exception{
        if (ObjectUtils.isEmpty(paraMap)){
            throw new Exception("传输数据不能为空");
        }
        List<NameValuePair> paraList = new ArrayList<NameValuePair>();
        for (Map.Entry<String , String> entry : paraMap.entrySet()){
            paraList.add(new BasicNameValuePair(entry.getKey() , entry.getValue()));
        }
        //对接口数据进行编码
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(paraList , Consts.UTF_8);
        return httpPostRequest(accessUrl,urlEncodedFormEntity,connectTimeout , connectionRequestTimeout , socketTimeout);
    }

    /**
     * 公共http请求方法
     * @param accessUrl 请求链接
     * @param stringEntity 请求参数实体
     * @param connectTimeout 超时时间
     * @param connectionRequestTimeout 连接请求超时时间
     * @param socketTimeout 读取超时时间
     * @return 返回结果
     */
    private static String httpPostRequest(String accessUrl , StringEntity stringEntity
            , int connectTimeout, int connectionRequestTimeout , int socketTimeout) throws Exception{
        if (StringUtils.isEmpty(accessUrl)){
            throw new Exception("请求地址不能为空");
        }
        if (ObjectUtils.isEmpty(connectTimeout)){
            connectTimeout = Constant.NUM_INT_5000;
        }
        if (ObjectUtils.isEmpty(connectionRequestTimeout)){
            connectionRequestTimeout = Constant.NUM_INT_5000;
        }
        if (ObjectUtils.isEmpty(socketTimeout)){
            socketTimeout = Constant.NUM_INT_5000;
        }
        String resultStr = null;
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse closeableHttpResponse = null;
        //生成http请求
        HttpPost httpPost = new HttpPost(accessUrl);
        httpPost.setEntity(stringEntity);
        //设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout).build();
        httpPost.setConfig(requestConfig);
        //请求并获取返回结果
        try {
            closeableHttpResponse = closeableHttpClient.execute(httpPost);
            //判断是否返回正确的状态码，若正常则获取返回数据
            if (Constant.NUM_INT_200 == closeableHttpResponse.getStatusLine().getStatusCode()){
                HttpEntity httpEntity = closeableHttpResponse.getEntity();
                //获取的数据为不为空则转换为字符串
                if (ObjectUtils.isNotEmpty(httpEntity)){
                    resultStr = EntityUtils.toString(httpEntity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ObjectUtils.isNotEmpty(closeableHttpClient)){
                    closeableHttpClient.close();
                }
                if (ObjectUtils.isNotEmpty(closeableHttpResponse)){
                    closeableHttpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultStr;
    }

    /**
     * 采用默认时间post请求，默认5s
     * @param accessUrl 请求地址
     * @param jsonStr 请求json串
     * @return 返回结果
     */
    public static String httpPostStrByDefaultTime(String accessUrl , String jsonStr) throws Exception{
        return sendMsgByPostMethod(accessUrl , jsonStr , Constant.NUM_INT_5000 , Constant.NUM_INT_5000 , Constant.NUM_INT_5000);
    }

    /**
     * 采用默认时间post请求，默认5s
     * @param accessUrl 请求地址
     * @param paraMap 请求参数Map形式
     * @return 返回结果
     */
    public static String httpPostMapByDefaultTime(String accessUrl , Map<String , String> paraMap) throws Exception{
        return sendMapMsgByPost(accessUrl , paraMap , Constant.NUM_INT_5000 ,Constant.NUM_INT_5000,Constant.NUM_INT_5000);
    }
}
