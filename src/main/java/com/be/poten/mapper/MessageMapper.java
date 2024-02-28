package com.be.poten.mapper;

import com.be.poten.domain.Message;
import com.be.poten.dto.message.GetMessageResponseDto;
import com.be.poten.dto.message.UpdateMessageRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {

    void insertMessage(Message message);

    GetMessageResponseDto getMessage(String messageId);

    void updateMessage(Message message);
}
