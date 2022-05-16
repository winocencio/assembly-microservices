package com.winocencio.assembly.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SessionTest {
	
	@Test
	public void whenVoteResultIsNotOverReturnVoteResultIsOpen() {
		
		Session session = new Session()
				.setMinutesToEnd(100L)
				.setDateTimeStarted(LocalDateTime.now());
		
		assertEquals(VoteResultEnum.OPEN,session.getVoteResult());
	}
	
	@Test
	public void whenVoteResultIsOverAndHave1VoteNoAnd1VoteYesReturnVoteResultDraw() {
		
		Session session = new Session()
				.setMinutesToEnd(1L)
				.setDateTimeStarted(LocalDateTime.of(2022, Month.MAY, 15, 23, 35, 0))
				.setVotes(List.of(
						new Vote().setVoteChoice(VoteChoiceEnum.NO),
						new Vote().setVoteChoice(VoteChoiceEnum.YES)
				));
		
		assertEquals(VoteResultEnum.DRAW,session.getVoteResult());
	}
	
	@Test
	public void whenVoteResultIsOverAndNoOneVoteReturnVoteResultDraw() {
		
		Session session = new Session()
				.setMinutesToEnd(1L)
				.setDateTimeStarted(LocalDateTime.of(2022, Month.MAY, 15, 23, 35, 0))
				.setVotes(Collections.emptyList());
		
		assertEquals(VoteResultEnum.DRAW,session.getVoteResult());
	}
	
	@Test
	public void whenVoteResultIsOverAndHave1VoteYesReturnVoteResultYes() {
		
		Session session = new Session()
				.setMinutesToEnd(1L)
				.setDateTimeStarted(LocalDateTime.of(2022, Month.MAY, 15, 23, 35, 0))
				.setVotes(List.of(
						new Vote().setVoteChoice(VoteChoiceEnum.YES)
				));
		
		assertEquals(VoteResultEnum.YES,session.getVoteResult());
	}
	
	@Test
	public void whenVoteResultIsOverAndHave1VoteNoReturnVoteResultNo() {
		
		Session session = new Session()
				.setMinutesToEnd(1L)
				.setDateTimeStarted(LocalDateTime.of(2022, Month.MAY, 15, 23, 35, 0))
				.setVotes(List.of(
						new Vote().setVoteChoice(VoteChoiceEnum.NO)
				));
		
		assertEquals(VoteResultEnum.NO,session.getVoteResult());
	}

}
