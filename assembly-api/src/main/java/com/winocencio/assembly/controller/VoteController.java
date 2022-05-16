package com.winocencio.assembly.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.winocencio.assembly.config.handler.ResponseDetails;
import com.winocencio.assembly.dto.VoteRequest;
import com.winocencio.assembly.model.Vote;
import com.winocencio.assembly.service.VoteService;

@RestController
public class VoteController {
	
	@Autowired
	private VoteService voteService;

	@PostMapping("/vote")
	public ResponseEntity<?> toVote(@RequestBody @Valid VoteRequest voteRequest){
		voteService.toVote(Vote.of(voteRequest));
		
		return new ResponseDetails<String>(HttpStatus.CREATED.value(),"The vote was created.")
				.buildResponseEntity();
	}
}
