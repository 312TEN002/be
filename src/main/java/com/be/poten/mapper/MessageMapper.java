package com.be.poten.mapper;

import com.be.poten.domain.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {

    void insertMessage(Message message);

}
