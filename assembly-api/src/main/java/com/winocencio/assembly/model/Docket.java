package com.winocencio.assembly.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

import com.winocencio.assembly.dto.DocketRequest;
import com.winocencio.assembly.dto.DocketResponse;

@Entity
public class Docket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	public Docket() {}
	
	public Docket(Integer id) {
		this.id = id;
	}

	public static Docket of(DocketResponse docketDto) {
		var docket = new Docket();
		BeanUtils.copyProperties(docketDto, docket);
		return docket;
	}
	
	public static Docket of(DocketRequest docketDto) {
		var docket = new Docket();
		BeanUtils.copyProperties(docketDto, docket);
		return docket;
	}
	
	public static List<Docket> of(List<DocketRequest> dockets) {
		return dockets.stream().map(Docket::of).collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
