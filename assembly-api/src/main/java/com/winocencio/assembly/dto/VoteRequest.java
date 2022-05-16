package com.winocencio.assembly.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.winocencio.assembly.model.VoteChoiceEnum;

public class VoteRequest {

	@NotNull
	private Integer sessionId;
	
	@NotBlank @CPF @Length(min = 11, max=11)
	private String associateCpf;
	
	@NotNull
	private VoteChoiceEnum voteChoice;
	
	public String getAssociateCpf() {
		return associateCpf;
	}
	public void setAssociateCpf(String associateCpf) {
		this.associateCpf = associateCpf;
	}
	
	public VoteChoiceEnum getVoteChoice() {
		return voteChoice;
	}
	public void setVoteChoice(VoteChoiceEnum voteChoice) {
		this.voteChoice = voteChoice;
	}
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
}
