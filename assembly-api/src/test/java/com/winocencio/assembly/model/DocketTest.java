package com.winocencio.assembly.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.Test;

public class DocketTest {
	
	@Test
	public void whenSessionResultIsNullReturnSessionResultIsNotStarted() {
		assertEquals(VoteResultEnum.NOT_STARTED,new Docket().getSessionResult());
	}
	
	@Test
	public void whenSessionResultIsNotNullReturnSessionResultIsStarted() {
		assertNotEquals(VoteResultEnum.NOT_STARTED,new Docket()
														.setSession(new Session()
																.setDateTimeStarted(LocalDateTime.now())
																.setMinutesToEnd(1L)
																.setVotes(Collections.emptyList()))
														.getSessionResult());
	}

}
