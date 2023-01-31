package com.payhada.admin.exception;

import com.payhada.admin.code.ErrorCode;
import com.payhada.admin.common.setting.Response;
import com.payhada.admin.common.util.MessageSourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpSession;

import static com.payhada.admin.common.util.MessageSourceUtils.getMessage;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

	@ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Response> handleBusinessException(BusinessException e, HttpSession session) {
		log.error("OCCURRED BUSINESS EXCEPTION :: {}", e.getMessage());
		ErrorCode errorCode = e.getError();
		Response res;

		if (errorCode == null) {
			res = Response.builder()
					.resultCode(500)
					.error(Response.Error.builder()
							.code(ErrorCode.API_SERVER_ERROR.getCode())
							.message(getMessage(ErrorCode.API_SERVER_ERROR.getCode(), session))
							.build())
					.build();
		} else {
			res = Response.builder()
					.resultCode(errorCode.getStatus())
					.error(Response.Error.builder()
							.code(errorCode.getCode())
							.message(getMessage(errorCode.getCode(), session))
							.build())
					.build();
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
    }

	@ExceptionHandler(Exception.class)
    protected ResponseEntity<Response> handleException(Exception e, HttpSession session) {
        log.error("OCCURRED EXCEPTION :: {}", e.getMessage());

		final ErrorCode errorCode = ErrorCode.API_SERVER_ERROR;

		final Response res = Response.builder()
				.resultCode(errorCode.getStatus())
				.error(Response.Error.builder()
						.code(errorCode.getCode())
						.message(MessageSourceUtils.getMessage(errorCode.getCode(), session))
						.build())
				.build();

		return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
