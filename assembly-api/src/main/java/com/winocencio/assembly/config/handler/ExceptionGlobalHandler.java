package com.winocencio.assembly.config.handler;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

@RestControllerAdvice
public class ExceptionGlobalHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {
		List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
		List<ExceptionFieldDetails> exceptionFieldDetails = new ArrayList<>();
		
		fieldErrors.forEach(e -> {
			String error = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			exceptionFieldDetails.add(new ExceptionFieldDetails(e.getField() ,error));
		});
		
		return new ResponseDetails<List<ExceptionFieldDetails>>(HttpStatus.BAD_REQUEST.value(),exceptionFieldDetails)
			.buildResponseEntity();
	}
	
	@ExceptionHandler(HttpMessageConversionException.class)
	public ResponseEntity<?> handleMessageNoteReadable(HttpMessageConversionException httpMessageNotReadableException) {
		return new ResponseDetails<String>(HttpStatus.BAD_REQUEST.value(),"Incorrect request.")
				.buildResponseEntity();
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleMethodNotSuported(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
		return new ResponseDetails<String>(HttpStatus.BAD_REQUEST.value(),httpRequestMethodNotSupportedException.getMessage())
				.buildResponseEntity();
	}
	
	@ExceptionHandler(RestClientException.class)
	public ResponseEntity<?> handleRestClient(RestClientException restClientException) {
		return new ResponseDetails<String>(HttpStatus.SERVICE_UNAVAILABLE.value(),restClientException.getMessage())
				.buildResponseEntity();
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidation(ValidationException restClientException) {
		return new ResponseDetails<String>(HttpStatus.BAD_REQUEST.value(),restClientException.getMessage())
				.buildResponseEntity();
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleConstrain(DataIntegrityViolationException dataIntegrityViolationException) {
		return new ResponseDetails<String>(HttpStatus.BAD_REQUEST.value(),"There are linked relationships.")
				.buildResponseEntity();
	}
}
