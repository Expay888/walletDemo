package com.star.wallet.utilis;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.util.HashMap;
import java.util.Map;

/**
 * 加解密工具类
 */
@Slf4j
public class SecureAesRsaUtil {

    public static Map<String, Object> genKeyPair(){
        RSA rsa = new RSA();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put("PUBLIC_KEY",rsa.getPublicKeyBase64());
        keyMap.put("PRIVATE_KEY", rsa.getPrivateKeyBase64());
        return keyMap;
    }


    /**
     * RSA公钥加密数据
     * @param data 数据
     * @param publicKey 公钥
     * @return
     */
    public static String publicEncryptData(String data, String publicKey){
        RSA rsa = new RSA(null, publicKey);
        byte[] encrypt = rsa.encrypt(StrUtil.bytes(data, CharsetUtil.UTF_8), KeyType.PublicKey);
        return Base64.encodeBase64String(encrypt);
    }


    /**
     * RSA私钥加密数据
     * @param data 数据
     * @param privateKey 私钥
     * @return String
     */
    public static String privatEncryptData(String data, String privateKey){
        RSA rsa = new RSA(privateKey, null);
        byte[] decrypt = rsa.encrypt(StrUtil.bytes(data, CharsetUtil.UTF_8), KeyType.PrivateKey);
        return Base64.encodeBase64String(decrypt);
    }

    /**
     * RSA私钥解密数据
     * @param data 数据
     * @param privateKey 私钥
     * @return String
     */
    public static String privatDecryptData(String data, String privateKey){
        RSA rsa = new RSA(privateKey, null);
        return rsa.decryptStr(data, KeyType.PrivateKey);
    }


    /**
     * RSA公钥解密数据
     * @param data 数据
     * @param publicKey 公钥
     * @return String
     */
    public static String publicDecryptData(String data, String publicKey){
        RSA rsa = new RSA(null, publicKey);
        return rsa.decryptStr(data, KeyType.PublicKey);
    }

}
