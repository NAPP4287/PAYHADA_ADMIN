package com.payhada.admin.exception;

import com.payhada.admin.config.error.ErrorCode;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(annotations = Controller.class)
@Order(9)
@Slf4j
public class ExceptionController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)	
	protected ResponseEntity<ErrorResponse> handleNullPointerException(MethodArgumentTypeMismatchException e){
		log.debug(getClass().getName()+"NullPointerException",e);
		final ErrorResponse res = new ErrorResponse();
		res.setErrorMsg("MethodArgumentTypeMismatchException");
		res.setErrorCode("0001");
		return new ResponseEntity<ErrorResponse>(res,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BusinessException.class)
    protected ModelAndView handleBusinessException(BusinessException e) {
        log.error(getClass().getName()+"handleBusinessException START");
		ErrorCode code = e.getError();
		ModelAndView mv = new ModelAndView();
		if(code==null) {			
			ErrorCode err=ErrorCode.NOT_DEFINED_ERR;
			mv.addObject("errCode",err.getCode());	
			mv.addObject("errMsg",err.getMessage());
		}else {
			mv.addObject("errCode",code.getCode());				
			mv.addObject("errMsg",code.getMessage());	
		}		
		mv.setViewName("/error/500");
		
		return mv;
    }

	@ExceptionHandler(ApplicationException.class)
	protected ModelAndView handleApplicationException(ApplicationException e) {
	    log.error(getClass().getName()+"Application Exception", e);
		ErrorCode code = e.getError();
		ModelAndView mv = new ModelAndView();
		if(code==null) {			
			ErrorCode err=ErrorCode.NOT_DEFINED_ERR;
			mv.addObject("errCode",err.getCode());	
			mv.addObject("errMsg",err.getMessage());
		}else {
			mv.addObject("errCode",code.getCode());				
			mv.addObject("errMsg",code.getMessage());	
		}		
		mv.setViewName("/error/500");			
		return mv;
	}

	@ExceptionHandler(Exception.class)
    protected ModelAndView handleException(Exception e) throws ApplicationException{
        log.error(getClass().getName()+":::: GLOBAL EXCEPTION", e);
        
		ModelAndView mv = new ModelAndView();
		ErrorCode err=ErrorCode.BUSINESS_PROCESS_ERR;
		mv.addObject("errCode",err.getCode());	
		mv.addObject("errMsg",err.getMessage());	
		mv.setViewName("/error/500");			
		return mv;
	}
    
}
