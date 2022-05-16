package com.winocencio.assembly.config.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseDetails<T> {
	
	private Integer status;
	private T message;
	
	public ResponseDetails(Integer status, T message) {
		this.status = status;
		this.message = message;
	}
	
	public ResponseEntity<?> buildResponseEntity(){
		return new ResponseEntity<>(this,HttpStatus.valueOf(status));
	}
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public T getMessage() {
		return message;
	}
	public void setMessage(T message) {
		this.message = message;
	}
}
