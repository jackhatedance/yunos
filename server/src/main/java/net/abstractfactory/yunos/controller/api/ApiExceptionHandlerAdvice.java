package net.abstractfactory.yunos.controller.api;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import net.abstractfactory.yunos.remote.exception.RemoteError;

@ControllerAdvice(basePackages = "net.abstractfactory.yunos.controller.api")
public class ApiExceptionHandlerAdvice {
	private Logger logger = Logger.getLogger(ApiExceptionHandlerAdvice.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public RemoteError exception(Exception exception, WebRequest request) {

		logger.error("MVC error:", exception);

		return new RemoteError(exception.getClass().getSimpleName(), exception
				.getClass().getCanonicalName(),
				getFirstNotNullMessage(exception),
				getDetailedMessage(exception));
	}

	private String getFirstNotNullMessage(Throwable throwable) {
		Throwable cause = throwable;

		while (true) {
			if (cause == null)
				break;

			if (cause.getMessage() != null)
				return cause.getMessage();

			cause = cause.getCause();

		}

		return "";
	}

	private String getDetailedMessage(Throwable throwable) {
		StringBuilder sb = new StringBuilder();
		sb.append(throwable.getMessage());

		Throwable cause = throwable;
		
		int i=0;
		int max=50;
		while (i++<max) {
			cause = cause.getCause();
			if (cause == null)
				break;

			sb.append(String.format("; caused by %s %s", cause.getClass()
					.getSimpleName(), cause.getMessage()));

		}
		sb.append(".");

		return sb.toString();
	}
}
