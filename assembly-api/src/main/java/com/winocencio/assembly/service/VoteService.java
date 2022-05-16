package com.winocencio.assembly.service;

import java.time.LocalDateTime;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winocencio.assembly.model.Session;
import com.winocencio.assembly.model.Vote;
import com.winocencio.assembly.repository.VoteRepository;

@Service
public class VoteService {
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private SessionService sessionService;
	
	public void toVote(Vote vote) {
		
		Session session = sessionService.getById(vote.getSession().getId());
		
		if(session.getDateTimeEnd().isBefore(LocalDateTime.now()))
			throw new ValidationException("Session has ended.");
		
		if(voteRepository.findBySessionAndAssociateCpf(vote.getSession(),vote.getAssociateCpf()).size() != 0)
			throw new ValidationException("The Associate has already voted in this session.");
		
		//CPF ESTÁ PERMITIDO?
			
		voteRepository.save(vote);
	}
}
