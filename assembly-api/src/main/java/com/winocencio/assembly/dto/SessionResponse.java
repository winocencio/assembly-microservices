package com.winocencio.assembly.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.winocencio.assembly.model.Session;

public class SessionResponse {

	private Integer id;
	private Long minutesToEnd;
	private LocalDateTime dateTimeStarted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDateTimeStarted() {
		return dateTimeStarted;
	}

	public void setDateTimeStarted(LocalDateTime dateTimeStarted) {
		this.dateTimeStarted = dateTimeStarted;
	}

	public Long getMinutesToEnd() {
		return minutesToEnd;
	}

	public void setMinutesToEnd(Long minutesToEnd) {
		this.minutesToEnd = minutesToEnd;
	}

	public static SessionResponse of(Session session) {
		var sessionResponse = new SessionResponse();
		BeanUtils.copyProperties(session,sessionResponse);
		return sessionResponse;
	}
}
