package com.winocencio.assembly.modules.docket.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
import com.winocencio.assembly.modules.docket.dto.DocketRequest;
import com.winocencio.assembly.modules.docket.dto.DocketResponse;
import com.winocencio.assembly.modules.docket.model.Docket;
import com.winocencio.assembly.modules.docket.service.DocketService;

@RestController
@RequestMapping(path="/docket")
public class DocketController {
	
	@Autowired
	private DocketService docketService;

	@PostMapping
	public ResponseEntity<DocketResponse> save(@RequestBody @Valid DocketRequest docketRequest){
		Docket docket = docketService.save(Docket.of(docketRequest));
		return new ResponseEntity<>(DocketResponse.of(docket),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<DocketResponse>> getAll(){
		List<Docket> dockets = docketService.getAll();
		return new ResponseEntity<>(DocketResponse.of(dockets),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DocketResponse> getById(@PathVariable @NotBlank Integer id){
		Docket docket = docketService.getById(id);
		return new ResponseEntity<>(DocketResponse.of(docket),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDetails<String>> deleteById(@PathVariable @NotBlank Integer id){
		docketService.deleteById(id);
		
		var responseDetails = new ResponseDetails<String>();
		responseDetails.setStatus(HttpStatus.OK.value());
		responseDetails.setMessage("The docket was deleted.");
		
		return new ResponseEntity<>(responseDetails,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DocketResponse> update(@PathVariable @NotBlank Integer id,@RequestBody @Valid DocketRequest docketRequest){
		Docket docket = docketService.update(id,Docket.of(docketRequest));
		return new ResponseEntity<>(DocketResponse.of(docket),HttpStatus.OK);
	}
}
