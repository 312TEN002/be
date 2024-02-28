package com.be.poten.controller;

import com.be.poten.common.response.ApiResponse;
import com.be.poten.dto.message.GetMessageResponseDto;
import com.be.poten.dto.message.MessageRequestDto;
import com.be.poten.dto.message.PostMessageResponseDto;
import com.be.poten.dto.message.UpdateMessageRequestDto;
import com.be.poten.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping(value = "/api/message")
    public ApiResponse<PostMessageResponseDto> executeAndGetMessage(@RequestBody MessageRequestDto message) {
        return ApiResponse.success(messageService.executeAndGetMessage(message));
    }

    @PostMapping(value = "/api/clova-study")
    public ResponseEntity<?> executeClovaStudy() throws Exception {
//        HashMap<String, String> result = new HashMap<>();
//        result.put("result", );
        messageService.executeClovaStudy();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/api/message/{messageId}")
    public ApiResponse<GetMessageResponseDto> getMessage(@PathVariable String messageId) {
        return ApiResponse.success(messageService.getMessage(messageId));
    }

    @PutMapping(value = "/api/message")
    public ApiResponse<?> updateMessage(@RequestBody UpdateMessageRequestDto message) {
        messageService.updateMessage(message);
        return ApiResponse.success();
    }
}