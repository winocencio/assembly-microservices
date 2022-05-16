package com.winocencio.assembly.model;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.beans.BeanUtils;

import com.winocencio.assembly.dto.SessionRequest;

@Entity
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "FK_DOCKET", nullable = false, unique = true)
	private Docket docket;

	@Column(name = "DATE_TIME_STARTED", nullable = false)
	private LocalDateTime dateTimeStarted;
	
	@Column(name = "MINUTES_TO_END", nullable = false)
	private Long minutesToEnd;
	
	
	public Session() {}
	
	public Session(Integer id) {
		this.id = id;
	}
	
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
	public Docket getDocket() {
		return docket;
	}
	public void setDocket(Docket docket) {
		this.docket = docket;
	}
	
	public Long getMinutesToEnd() {
		return minutesToEnd;
	}
	public void setMinutesToEnd(Long minutesToEnd) {
		this.minutesToEnd = minutesToEnd;
	}
	
	public LocalDateTime getDateTimeEnd() {
		return this.getDateTimeStarted().plus(Duration.ofMinutes(this.getMinutesToEnd()));
	}
	
	public static Session of(SessionRequest sessionDto) {
		var session = new Session();
		BeanUtils.copyProperties(sessionDto, session);
		session.setDocket(new Docket(sessionDto.getDocketId()));
		return session;
	}
	
}
