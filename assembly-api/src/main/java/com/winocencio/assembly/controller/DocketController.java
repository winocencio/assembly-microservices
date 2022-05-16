package com.winocencio.assembly.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winocencio.assembly.config.handler.ResponseDetails;
import com.winocencio.assembly.dto.DocketRequest;
import com.winocencio.assembly.dto.DocketResponse;
import com.winocencio.assembly.model.Docket;
import com.winocencio.assembly.service.DocketService;

@RestController
@RequestMapping(path="/docket")
public class DocketController {
	
	@Autowired
	private DocketService docketService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid DocketRequest docketRequest){
		Docket docket = docketService.save(Docket.of(docketRequest));
		return new ResponseEntity<>(DocketResponse.of(docket),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<Docket> dockets = docketService.getAll();
		return new ResponseEntity<>(DocketResponse.of(dockets),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable @NotBlank Integer id){
		Docket docket = docketService.getById(id);
		return new ResponseEntity<>(DocketResponse.of(docket),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable @NotBlank Integer id){
		docketService.deleteById(id);
		
		return new ResponseDetails<String>(HttpStatus.OK.value(),"The docket was deleted.")
				.buildResponseEntity();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable @NotBlank Integer id,@RequestBody @Valid DocketRequest docketRequest){
		Docket docket = docketService.update(id,Docket.of(docketRequest));
		return new ResponseEntity<>(DocketResponse.of(docket),HttpStatus.OK);
	}
}
