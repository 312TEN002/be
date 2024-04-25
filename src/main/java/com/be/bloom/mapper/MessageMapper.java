package com.be.bloom.mapper;

import com.be.bloom.domain.Message;
import com.be.bloom.dto.message.GetMessageResponseDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {

    void insertMessage(Message message);

    GetMessageResponseDto getMessage(String messageId);

    GetMessageResponseDto getMessageByShareKey(String shareKey);

    void updateMessage(Message message);

    String getMessageShareKey();

}
