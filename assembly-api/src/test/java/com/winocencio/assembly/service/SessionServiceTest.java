package com.winocencio.assembly.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.winocencio.assembly.model.Session;

public class SessionServiceTest {
	
	
	@Test
	public void whenMinutesToEndWasNotInformedPutMinuteDefaultInSession() {
	
		Session session = new Session()
				.setMinutesToEnd(SessionService.MINUTES_TO_END_NOT_INFORMED);
		
		new SessionService().formatMinutesToEnd(session);
		
		assertEquals(SessionService.MINUTES_TO_END_DEFAULT,session.getMinutesToEnd());
	}
	
	@Test
	public void whenMinutesToEndIsNullPutMinuteDefaultInSession() {
	
		Session session = new Session();
		
		new SessionService().formatMinutesToEnd(session);
		
		assertEquals(SessionService.MINUTES_TO_END_DEFAULT,session.getMinutesToEnd());
	}
	
	@Test
	public void whenMinutesToEndWasInformedUseInformedMinute() {
	
		Session session = new Session()
				.setMinutesToEnd(10L);
		
		new SessionService().formatMinutesToEnd(session);
		
		assertEquals(10L,session.getMinutesToEnd());
	}
}
