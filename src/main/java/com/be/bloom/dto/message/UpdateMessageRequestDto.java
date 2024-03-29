package com.be.bloom.dto.message;

import lombok.Data;

@Data
public class UpdateMessageRequestDto {

    private String messageId;
    private String resultUpdateData;

}