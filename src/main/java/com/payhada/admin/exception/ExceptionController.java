package com.payhada.admin.exception;

import com.payhada.admin.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)	
	protected ResponseEntity<ErrorResponse> handleNullPointerException(MethodArgumentTypeMismatchException e){
		log.debug(getClass().getName() + "NullPointerException",e);

		final ErrorResponse res = ErrorResponse.builder()
				.errorCode("0001")
				.errorMsg("MethodArgumentTypeMismatchException")
				.build();

		return new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BusinessException.class)
    protected ModelAndView handleBusinessException(BusinessException e) {
        log.error(getClass().getName() + "handleBusinessException START");

		ErrorCode code = e.getError();
		ModelAndView mv = new ModelAndView();

		if(code == null) {
			ErrorCode err=ErrorCode.API_SERVER_ERROR;
			mv.addObject("errCode", err.getCode());
			mv.addObject("errMsg", err.getMessage());
		}else {
			mv.addObject("errCode", code.getCode());
			mv.addObject("errMsg", code.getMessage());
		}

		mv.setViewName("/error/500");
		
		return mv;
    }

	@ExceptionHandler(Exception.class)
    protected ModelAndView handleException(Exception e) {
        log.error(getClass().getName() + ":::: GLOBAL EXCEPTION", e);
        
		ErrorCode err = ErrorCode.API_PROCESS_ERROR;

		ModelAndView mv = new ModelAndView();
		mv.addObject("errCode", err.getCode());
		mv.addObject("errMsg", err.getMessage());
		mv.setViewName("/error/500");

		return mv;
	}
}
