package com.payhada.admin.exception;

import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.common.setting.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

	@ExceptionHandler(BusinessException.class)
    protected ResponseEntity<CommonResponse> handleBusinessException(BusinessException e) {
		log.error("OCCURRED BUSINESS EXCEPTION :: {}", e.getResponseCode().getCode());

		ResponseCode responseCode = e.getResponseCode();

		if (responseCode == null) {
			responseCode = ResponseCode.API_SERVER_ERROR;
		}

		return responseCode.toResponseEntity(e.getData());
    }

	@ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResponse> handleException(Exception e) {
        log.error("OCCURRED EXCEPTION :: {} - {}", e.getClass().getName(), e.getMessage());

		final ResponseCode responseCode = ResponseCode.API_SERVER_ERROR;

		return responseCode.toResponseEntity();
    }
}
