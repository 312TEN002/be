package com.be.poten.dto.message;

import com.be.poten.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostMessageResponseDto {

    private String messageId;
    private String resultData;

    public static PostMessageResponseDto PostMessageResponseDtoOf(Message message) {
        return new PostMessageResponseDto(message.getMessageId(), message.getResultData());
    }
    
}