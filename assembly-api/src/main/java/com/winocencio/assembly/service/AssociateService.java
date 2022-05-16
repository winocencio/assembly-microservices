package com.winocencio.assembly.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.winocencio.assembly.dto.AssociateResponse;
import com.winocencio.assembly.model.VotePermissionEnum;

@Service
public class AssociateService {
	
	private RestTemplate restTemplate;
	
	public AssociateService() {
		this.restTemplate = new RestTemplate();
	}
	
	public void validateVotePermission(String associateCpf) {
		try {
			
			ResponseEntity<AssociateResponse> result = restTemplate.getForEntity(new URI(MessageFormat.format("https://user-info.herokuapp.com/users/{0}", associateCpf)), AssociateResponse.class);
			
			if(result.getStatusCode().equals(HttpStatus.NOT_FOUND)) 
				throw new ValidationException("Something wrong with associateCpf.");
			
			if(!result.getStatusCode().equals(HttpStatus.OK)) 
				throw new RestClientException("Error on user service.");
			
			if(result.getBody().getStatus().equals(VotePermissionEnum.UNABLE_TO_VOTE))
				throw new ValidationException("That associate is unable to vote.");
			
		} catch (RestClientException | URISyntaxException e) {
			throw new RestClientException("Error on user service.");
		}
	}

}
