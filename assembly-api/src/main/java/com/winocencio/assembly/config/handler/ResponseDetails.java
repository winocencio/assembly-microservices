package com.winocencio.assembly.config.handler;

public class ResponseDetails<T> {
	
	private Integer status;
	private T message;
	
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
