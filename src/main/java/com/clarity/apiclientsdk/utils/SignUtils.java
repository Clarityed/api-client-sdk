package com.clarity.apiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * 签名工具
 *
 * @author: clarity
 * @date: 2022年11月10日 19:10
 */
public class SignUtils {

    public static String getSign(String body, String secretKey) {
        // 摘要算法可能和效率有关
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body + "keQing" + secretKey;
        return md5.digestHex(content);
    }

}
