package com.be.bloom.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class SignitureUtils {

    public static String makeSignature(String timeStamp, String method, String url, String accessKey, String secretKey) throws Exception {
        String message = new StringBuilder()
                .append(method)
                .append(" ")
                .append(url)
                .append("\n")
                .append(timeStamp)
                .append("\n")
                .append(accessKey)
                .toString();
        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);
        return encodeBase64String;
    }

}
