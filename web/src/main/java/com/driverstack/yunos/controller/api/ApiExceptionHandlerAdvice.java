package com.driverstack.yunos.controller.api;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionHandlerAdvice {

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public RestApiError exception(Exception exception, WebRequest request) {
		StringWriter errors = new StringWriter();
		exception.printStackTrace(new PrintWriter(errors));
		
		return new RestApiError(errors.toString());
	}
}
