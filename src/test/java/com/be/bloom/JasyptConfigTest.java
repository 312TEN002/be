package com.be.bloom;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

public class JasyptConfigTest {

    @Test
    void stringEncryptor() {
        String value = "암호화할 값(수정 필요)";
        System.out.println(jasyptEncoding(value));
    }

    public String jasyptEncoding(String value) {

        String key = "jasypt 암호화 비밀번호(수정 필요)";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }

}
