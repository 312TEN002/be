package com.be.poten.dto.message;

import lombok.Data;

@Data
public class MessageRequestDto {

    private String targetType;
    private String targetName;
    private String relationship;
    private String userName;
    private String concept;
    private String story;
    private String speechType;
    private String lastComment;
    private String minute;

    private Boolean isRenew; // 재생성 여부
}