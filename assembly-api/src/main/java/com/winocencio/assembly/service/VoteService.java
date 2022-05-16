package com.winocencio.assembly.service;

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
	
	@Autowired
	private AssociateService associateService;
	
	public void toVote(Vote vote) {
		
		Session session = sessionService.getById(vote.getSession().getId());
		
		if(session.isOver())
			throw new ValidationException("Session has ended.");
		
		if(voteRepository.findBySessionAndAssociateCpf(vote.getSession(),vote.getAssociateCpf()).size() != 0)
			throw new ValidationException("The Associate has already voted in this session.");
		
		associateService.validateVotePermission(vote.getAssociateCpf());
			
		voteRepository.save(vote);
	}
}
