package com.hml.admin.exception;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hml.core.http.HttpResult;

@RestControllerAdvice
public class GlobalException {

	private Logger log = Logger.getLogger(GlobalException.class);
	
	@ExceptionHandler(value=java.lang.Exception.class)
	public HttpResult exceptionHandle(Exception e){
		log.error(e.getMessage());
		e.printStackTrace();
		
		return HttpResult.error(e.getMessage());
	}
}
