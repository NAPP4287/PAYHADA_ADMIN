package com.payhada.admin.exception;

import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.common.setting.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

	@ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Response> handleBusinessException(BusinessException e) {
		log.error("OCCURRED BUSINESS EXCEPTION :: {}", e.getMessage());

		ResponseCode responseCode = e.getResponseCode();

		if (responseCode == null) {
			responseCode = ResponseCode.API_SERVER_ERROR;
		}

		return responseCode.toResponseEntity();
    }

	@ExceptionHandler(Exception.class)
    protected ResponseEntity<Response> handleException(Exception e) {
        log.error("OCCURRED EXCEPTION :: {}", e.getMessage());

		final ResponseCode responseCode = ResponseCode.API_SERVER_ERROR;

		return responseCode.toResponseEntity();
    }
}
