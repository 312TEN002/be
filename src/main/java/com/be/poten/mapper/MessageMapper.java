package com.be.poten.mapper;

import com.be.poten.domain.Message;
import com.be.poten.dto.message.GetMessageResponseDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {

    void insertMessage(Message message);

    GetMessageResponseDto getMessage(String messageId);
}
