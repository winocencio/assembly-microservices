package com.winocencio.assembly.service;

import java.time.LocalDateTime;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winocencio.assembly.model.Docket;
import com.winocencio.assembly.model.Session;
import com.winocencio.assembly.repository.SessionRepository;

@Service
public class SessionService {
	
	static final Long MINUTES_TO_END_NOT_INFORMED = 0L;
	static final Long MINUTES_TO_END_DEFAULT = 1L;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private DocketService docketService;

	public Session getById(Integer id) {
		return sessionRepository.findById(id).orElseThrow(() -> new ValidationException("There's no session for the given ID."));
	}
	
	public Session getByDocketId(Integer docketId) {
		
		Docket docket = new Docket();
		docket.setId(docketId);
		return sessionRepository.findByDocket(docket).orElseThrow(() -> new ValidationException("There's no session for the given ID."));
	}

	public Session startSession(Session session) {
		
		session.setDocket(docketService.getById(session.getDocket().getId()));
		
		if(sessionRepository.findByDocket(session.getDocket()).isPresent())
			throw new ValidationException("The session has already started.");

		session.setDateTimeStarted(LocalDateTime.now());
		formatMinutesToEnd(session);
		
		return sessionRepository.save(session);
	}

	void formatMinutesToEnd(Session session) {
		if(session.getMinutesToEnd() == null || session.getMinutesToEnd().equals(MINUTES_TO_END_NOT_INFORMED))
			session.setMinutesToEnd(MINUTES_TO_END_DEFAULT);
	}
}
