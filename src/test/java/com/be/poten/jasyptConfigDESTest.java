package com.be.poten;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;


class JasyptConfigDESTest {

    @Test
    void stringEncryptor() {
        String url = "jdbc:mariadb://3.36.218.160:3306/potenday?allowMultiQueries=true";
        String username = "root";
        String password = "1q1q";

        System.out.println(jasyptEncoding(url));
        System.out.println(jasyptEncoding(username));
        System.out.println(jasyptEncoding(password));
    }

    public String jasyptEncoding(String value) {

        String key = "암호화할 때 사용할 키";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }
}