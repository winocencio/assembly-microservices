package com.winocencio.assembly.config.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionGlobalHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {
		List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
		var responseDetails = new ResponseDetails<List<ExceptionFieldDetails>>();
		List<ExceptionFieldDetails> exceptionFieldDetails = new ArrayList<>();
		
		fieldErrors.forEach(e -> {
			String error = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			exceptionFieldDetails.add(new ExceptionFieldDetails(e.getField() ,error));
		});
		
		responseDetails.setStatus(HttpStatus.BAD_REQUEST.value());
		responseDetails.setMessage(exceptionFieldDetails);
		
		return new ResponseEntity<>(responseDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidation(ValidationException validationException) {
		var responseDetails = new ResponseDetails<String>();
		
		responseDetails.setStatus(HttpStatus.BAD_REQUEST.value());
		responseDetails.setMessage(validationException.getMessage());
		
		return new ResponseEntity<>(responseDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageConversionException.class)
	public ResponseEntity<?> handleMessageNoteReadable(HttpMessageConversionException httpMessageNotReadableException) {
		var responseDetails = new ResponseDetails<String>();
		
		responseDetails.setStatus(HttpStatus.BAD_REQUEST.value());
		responseDetails.setMessage("Incorrect request");
		
		return new ResponseEntity<>(responseDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleMethodNotSuported(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
		var responseDetails = new ResponseDetails<String>();
		
		responseDetails.setStatus(HttpStatus.BAD_REQUEST.value());
		responseDetails.setMessage(httpRequestMethodNotSupportedException.getMessage());
		
		return new ResponseEntity<>(responseDetails,HttpStatus.BAD_REQUEST);
	}
}
