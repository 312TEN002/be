package com.be.poten.domain;

import com.be.poten.dto.message.MessageRequestDto;
import com.be.poten.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {

    private String messageId;
    private String userId;
    private String paramData;
    private String resultData;

    public static Message MessageOf(MessageRequestDto message, String result) {
        return new Message(null,null, JsonUtils.toJson(message), result);
    }
}