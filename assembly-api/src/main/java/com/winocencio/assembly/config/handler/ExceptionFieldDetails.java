package com.winocencio.assembly.config.handler;

public class ExceptionFieldDetails {
	
	private String field;
	private String error;
	
	public ExceptionFieldDetails(String field,String error) {
		this.setField(field);
		this.setError(error);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
