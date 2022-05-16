package com.winocencio.assembly.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winocencio.assembly.model.Docket;
import com.winocencio.assembly.repository.DocketRepository;

@Service
public class DocketService {
	
	@Autowired
	private DocketRepository docketRepository;
	
	public Docket save(Docket docket) {
		return docketRepository.save(docket);
	}

	public List<Docket> getAll() {
		return docketRepository.findAll();
	}

	public Docket getById(Integer id) {
		return docketRepository.findById(id).orElseThrow(() -> new ValidationException("There's no docket for the given ID."));
	}

	public void deleteById(Integer id) {
		
		Docket docketDb = docketRepository.findById(id)
		.orElseThrow(() -> new ValidationException("There's no docket for the given ID."));
		
		docketRepository.deleteById(docketDb.getId());
	}

	public Docket update(Integer id, Docket docket) {
		return docketRepository.findById(id)
				.map(docketDb -> {
					docketDb.setName(docket.getName());
					docketDb.setDescription(docket.getDescription());
					return docketRepository.save(docketDb);
				})
				.orElseThrow(() -> new ValidationException("There's no docket for the given ID."));
	}
}
