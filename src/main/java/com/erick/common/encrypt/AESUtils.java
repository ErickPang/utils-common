package com.erick.common.encrypt;


import com.erick.common.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
* AES加解密工具类
* @author  pangshaoshan
* @date   2020/4/22 4:45 下午
*
*/
@Slf4j
public class AESUtils {

    private static final String KEY_AES = "AES";
    private static final String KEY_MD5 = "MD5";

    private static MessageDigest md5Digest;
    static {
        try {
            md5Digest = MessageDigest.getInstance(KEY_MD5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    /**
    * 加密数据
    * @author  pangshaoshan
    * @date  2020/4/22 4:46 下午
    * @param data 加密数据
    * @param key 加密key
    * @return 加密后的数据
    *
    */
    public static String encrypt(String data, String key) throws Exception {
        return doAes(data, key, Cipher.ENCRYPT_MODE);
    }

    /**
    * @deprecated 解密数据
    * @author  pangshaoshan
    * @date  2020/4/22 4:48 下午
    * @param data 解密数据
    * @param key 密钥
    * @return 解密后的数据
    *
    */
    public static String decrypt(String data, String key) throws Exception {
        return doAes(data, key, Cipher.DECRYPT_MODE);
    }

    /**
    * @deprecated 实际解密
    * @author  pangshaoshan
    * @date   2020/4/22 4:49 下午
    * @param data 解密数据
    * @param key 密钥
    * @param mode 模式
    * @return 加密或者解密的数据
    * @exception
    *
    */
    private static String doAes(String data, String key, int mode) throws Exception {
        byte[] result = null;
        try {
            if (StringUtils.isBlank(data) || StringUtils.isBlank(key)) {
                log.info("数据为空");
                return null;
            }
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            if (encrypt) {
                content = data.getBytes(Constant.UTF_8);
            } else {
                content = Base64.decodeBase64(data);
            }
            SecretKeySpec keySpec = new SecretKeySpec(md5Digest.digest(key.getBytes(Constant.UTF_8))
                    , KEY_AES);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(KEY_AES);
            // 初始化
            cipher.init(mode, keySpec);
            result = cipher.doFinal(content);
            if (encrypt) {
                return Base64.encodeBase64String(result);
            }
        } catch (Exception e) {
            log.error("AES 密文处理异常,e:{}", e);
            throw new Exception("处理发生异常，" , e);
        }
        return new String(result, Constant.UTF_8);
    }
}
