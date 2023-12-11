package com.be.poten.dto.ClovaRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClovaRequestMessage {

    private String role;
    private String content;

}
