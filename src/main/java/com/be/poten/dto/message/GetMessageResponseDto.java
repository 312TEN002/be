package com.be.poten.dto.message;

import lombok.Data;

@Data
public class GetMessageResponseDto {

    private String messageId;
    private String regDate;
    private String resultData;

}
