package com.be.poten.domain;

import com.be.poten.dto.ClovaRequestDto.PostClovaResponseDto;
import com.be.poten.dto.message.MessageRequestDto;
import com.be.poten.dto.message.UpdateMessageRequestDto;
import com.be.poten.utils.JsonUtils;
import com.be.poten.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String messageId;
    private String userId;
    private String paramData;
    private String paramClovaData;
    private String resultData;
    private String resultRowData;
    private String resultUpdateData;
    private String isRenew;
    private String isUpdate;


    /* 축사 생성 */
    public static Message MessageOf(MessageRequestDto message, PostClovaResponseDto result) {
        return new Message(null,null, JsonUtils.toJson(message), result.getClovaContent(), result.getResultData(), result.getResultRowData(), null, StringUtils.convertToDatabaseColumn(message.getIsRenew()), "N");
    }

    /* 축사 수정 이력 저장 */
    public static Message MessageOf(UpdateMessageRequestDto message) {
        Message resMessage = new Message();
        resMessage.setMessageId(message.getMessageId());
        resMessage.setResultUpdateData(message.getResultUpdateData());
        resMessage.setIsUpdate("Y");
        return resMessage;
    }
}