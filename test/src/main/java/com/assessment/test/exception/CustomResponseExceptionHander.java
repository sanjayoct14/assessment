package com.assessment.test.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseExceptionHander extends ResponseEntityExceptionHandler {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest req) {

		ExceptionResponse exeResp = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity(exeResp, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(InvoiceNotFoundException.class)
	public final ResponseEntity<Object> handleInvoiceNotFoundException(InvoiceNotFoundException ex, WebRequest req) {

		ExceptionResponse exeResp = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity(exeResp, HttpStatus.NOT_FOUND);
	}
}
