package com.be.bloom.common.response;

import lombok.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    private Status status;
    private String code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T data, String message) {
        return (ApiResponse<T>) ApiResponse.builder()
                .status(Status.SUCCESS)
                .code("200")
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success() {
        return success(null, "성공");
    }

    public static <T> ApiResponse<T> success(T data) {
        return success(data, "성공");
    }

    public static <T> ApiResponse<T> success(String message) {
        return success(null, message);
    }

    public static <T> ApiResponse<T> fail(T data, String message, String code) {
        return (ApiResponse<T>) ApiResponse.builder()
                .status(Status.ERROR)
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> fail(String message, String code) {
        return (ApiResponse<T>) ApiResponse.builder()
                .status(Status.ERROR)
                .code(code)
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> fail(String code) {
        return (ApiResponse<T>) ApiResponse.builder()
                .status(Status.ERROR)
                .code(code)
                .build();
    }

    // Validator에 의해 유효하지 않은 데이터로 인해 API 호출이 거부될때 반환
    public static <T> ApiResponse<T> fieldError(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();

        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError error : allErrors) {
            if (error instanceof FieldError) {
                errors.put(((FieldError) error).getField(), error.getDefaultMessage());
            } else {
                errors.put( error.getObjectName(), error.getDefaultMessage());
            }
        }
        return (ApiResponse<T>) ApiResponse.builder()
                .status(Status.FAIL)
                .code("400")
                .message("데이터 유효성 검사 실패 했습니다.")
                .data(errors)
                .build();
    }

    public static <T> ApiResponse<T> fieldError(T data, String message, String code) {
        return (ApiResponse<T>) ApiResponse.builder()
                .status(Status.FAIL)
                .code(code)
                .message(message)
                .data(data)
                .build();
    }


    public enum Status {
        SUCCESS,    // 성공
        ERROR,      // 예외
        FAIL        // 데이터 유효성 오류
    }
}