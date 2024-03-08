package com.be.poten;

import org.junit.jupiter.api.Test;

public class MessageTest {

    @Test
    void messageReplaceTest() {
        String beforeMessage = "안녕, 신랑 이름! 신부 이름언니와 결혼 축하해.";
        String targetType = "신부";
        String targetName = "지은";

        String afterMessage = ("신랑".equals(targetType)) ? beforeMessage.replaceAll("신랑 이름", targetName) : beforeMessage.replaceAll("신부 이름", targetName);
        afterMessage = afterMessage.replaceAll("\\*", "").replaceAll("\\[", "").replaceAll("\\]", "");

        System.out.println("===== before =====");
        System.out.println(beforeMessage);
        System.out.println("===== after  =====");
        System.out.println(afterMessage);
    }
}
