package com.winocencio.assembly.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.BeanUtils;

import com.winocencio.assembly.dto.VoteRequest;

@Entity
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@ManyToOne()
	@JoinColumn(name = "FK_SESSION", nullable = false)
	private Session session;
	
	@Column(name = "ASSOCIATE_CPF", nullable = false)
	private String associateCpf;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "VOTE_CHOICE", nullable = false)
	private VoteChoiceEnum voteChoice;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

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

	public static Vote of(VoteRequest voteDto) {
		var vote = new Vote();
		BeanUtils.copyProperties(voteDto, vote);
		vote.setSession(new Session(voteDto.getSessionId()));
		return vote;
	}
}
