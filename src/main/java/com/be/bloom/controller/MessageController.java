package com.be.bloom.controller;

import com.be.bloom.common.response.ApiResponse;
import com.be.bloom.dto.message.GetMessageResponseDto;
import com.be.bloom.dto.message.MessageRequestDto;
import com.be.bloom.dto.message.PostMessageResponseDto;
import com.be.bloom.dto.message.UpdateMessageRequestDto;
import com.be.bloom.service.MessageService;
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

    @GetMapping(value = "/api/message")
    public ApiResponse<GetMessageResponseDto> getMessageByShareKey(@RequestParam String shareKey) {
        return ApiResponse.success(messageService.getMessageByShareKey(shareKey));
    }

    @PutMapping(value = "/api/message")
    public ApiResponse<?> updateMessage(@RequestBody UpdateMessageRequestDto message) {
        messageService.updateMessage(message);
        return ApiResponse.success();
    }
}