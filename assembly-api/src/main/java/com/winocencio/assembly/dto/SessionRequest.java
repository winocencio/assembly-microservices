package com.winocencio.assembly.dto;

import javax.validation.constraints.NotNull;

public class SessionRequest {

	@NotNull
	private Integer docketId;
	private Long minutesToEnd;

	public Long getMinutesToEnd() {
		return minutesToEnd;
	}

	public void setMinutesToEnd(Long minutesToEnd) {
		this.minutesToEnd = minutesToEnd;
	}

	public Integer getDocketId() {
		return docketId;
	}

	public void setDocketId(Integer docketId) {
		this.docketId = docketId;
	}
}
