package com.be.poten.domain;

import com.be.poten.dto.ClovaRequestDto.PostClovaResponseDto;
import com.be.poten.dto.message.MessageRequestDto;
import com.be.poten.utils.JsonUtils;
import com.be.poten.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {

    private String messageId;
    private String userId;
    private String paramData;
    private String paramClovaData;
    private String resultData;
    private String resultRowData;
    private String isRenew;

    public static Message MessageOf(MessageRequestDto message, PostClovaResponseDto result) {
        return new Message(null,null, JsonUtils.toJson(message), result.getClovaContent(), result.getResultData(), result.getResultRowData(), StringUtils.convertToDatabaseColumn(message.getIsRenew()));
    }
}