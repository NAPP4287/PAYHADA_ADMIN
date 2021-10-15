package com.payhada.admin.exception;

import com.payhada.admin.config.error.ErrorCode;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice(annotations = RestController.class)
@Order(0)
@Slf4j
public class RestExceptionController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)	
	protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
		log.debug("MethodArgumentTypeMismatchException",e);
		final ErrorResponse res = new ErrorResponse();
		res.setErrorMsg("MethodArgumentTypeMismatchException");
		res.setErrorCode("0001");
		return new ResponseEntity<ErrorResponse>(res,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NullPointerException.class)	
	protected ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException e){
		log.debug("NullPointerException", e);
		final ErrorResponse res = new ErrorResponse();
		res.setErrorMsg("서비스 처리중 오류가 발생했습니다.\n다시 시도해주세요.");
		res.setErrorCode("0002");
		return new ResponseEntity<ErrorResponse>(res,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        log.error(":handleBusinessException START");
		final ErrorResponse res = new ErrorResponse();
		ErrorCode code =e.getError();
		
		if(code==null) {			
			ErrorCode err=ErrorCode.NOT_DEFINED_ERR;
			res.setErrorMsg(err.getMessage());
			res.setErrorCode(err.getCode());	
		}else {
			res.setErrorMsg(code.getMessage());	
			res.setErrorCode(code.getCode());				
		}
		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);    
    }

	@ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleApplicationException(Exception e) {
        log.error("GLOBAL EXCEPTION", e);
		final ErrorResponse res = new ErrorResponse();		
		ErrorCode err=ErrorCode.BUSINESS_PROCESS_ERR;
		res.setErrorMsg(err.getMessage());
		res.setErrorCode(err.getCode());					
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);    
    }
}
