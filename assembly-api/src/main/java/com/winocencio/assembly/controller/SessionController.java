package com.winocencio.assembly.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.winocencio.assembly.config.handler.ResponseDetails;
import com.winocencio.assembly.dto.SessionRequest;
import com.winocencio.assembly.dto.SessionResponse;
import com.winocencio.assembly.model.Session;
import com.winocencio.assembly.service.SessionService;

@RestController
public class SessionController {
	
	@Autowired
	private SessionService sessionService;

	@PostMapping("/startSession")
	public ResponseEntity<?> startSession(@RequestBody @Valid SessionRequest sessionRequest){
		Session startSession = sessionService.startSession(Session.of(sessionRequest));
		
		return new ResponseDetails<SessionResponse>(HttpStatus.CREATED.value(),SessionResponse.of(startSession))
				.buildResponseEntity();
	}
	
}
