package com.be.poten.common.response;

import lombok.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private String timestamp;

    public static <T> ApiResponse<T> success(T data, String message) {
        return (ApiResponse<T>) ApiResponse.builder()
                .status(Status.SUCCESS)
                .code("200")
                .message(message)
                .data(data)
                .timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()))
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

    public static <T> ApiResponse<T> error(T data, String message, String code) {
        return (ApiResponse<T>) ApiResponse.builder()
                .status(Status.ERROR)
                .code(code)
                .message(message)
                .data(data)
                .timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()))
                .build();
    }

    public static <T> ApiResponse<T> error(String message, String code) {
        return (ApiResponse<T>) ApiResponse.builder()
                .status(Status.ERROR)
                .code(code)
                .message(message)
                .timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()))
                .build();
    }

    public static <T> ApiResponse<T> error(String code) {
        return (ApiResponse<T>) ApiResponse.builder()
                .status(Status.ERROR)
                .code(code)
                .timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()))
                .build();
    }

    // Validator에 의해 유효하지 않은 데이터로 인해 API 호출이 거부될때 반환
    public static <T> ApiResponse<T> fail(BindingResult bindingResult) {
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
                .status(Status.SUCCESS)
                .code("400")
                .message("데이터 유효성 오류 입니다.")
                .data(errors)
                .timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()))
                .build();
    }


    public enum Status {
        SUCCESS,    // 성공
        ERROR,      // 예외
        FAIL        // 데이터 유효성 오류
    }
}