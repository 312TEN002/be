package com.be.poten.common.response;

import com.be.poten.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * http status: 400, code: FAIL
     * 비즈니스 로직 처리에서 에러가 발생함
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BaseException.class)
    public ApiResponse baseException(BaseException e) {
        log.warn("[BaseException] cause >>> {}, errorMsg >>> {}", NestedExceptionUtils.getMostSpecificCause(e), NestedExceptionUtils.getMostSpecificCause(e).getMessage());
        return ApiResponse.fail(e.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    /**
     * http status: 400, code: FIELD_FAIL
     * request parameter, form field 에러
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ApiResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("[methodArgumentNotValidException] errorMsg >>> {}", NestedExceptionUtils.getMostSpecificCause(e).getMessage());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        String message = "데이터 유효성 검사 실패 했습니다.";
        return ApiResponse.fieldError(errors, message, String.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    /**
     * http status: 500, code: FAIL
     * 시스템 예외 상황. 집중 모니터링 대상
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ApiResponse onException(Exception e) {
        log.error("[SeverErrorException] errorMsg >>> {} ", e.toString());
        return ApiResponse.fail( String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

}