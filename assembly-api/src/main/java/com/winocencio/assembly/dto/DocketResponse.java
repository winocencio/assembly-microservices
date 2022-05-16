package com.winocencio.assembly.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.winocencio.assembly.model.Docket;

public class DocketResponse {

	private Integer id;
	private String name;
	private String description;
	
	public static DocketResponse of(Docket docket) {
		var dto = new DocketResponse();
		BeanUtils.copyProperties(docket, dto);
		return dto;
	}
	
	public static List<DocketResponse> of(List<Docket> dockets) {
		return dockets.stream().map(DocketResponse::of).collect(Collectors.toList());
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
