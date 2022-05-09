package com.winocencio.assembly.modules.docket.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import com.winocencio.assembly.modules.docket.model.Docket;

public class DocketRequest {

	@NotNull @NotEmpty @Length(min = 5, max = 20)
	private String name;
	
	@NotNull @NotEmpty @Length(min = 5, max = 150)
	private String description;
	
	public static DocketRequest of(Docket docket) {
		var dto = new DocketRequest();
		BeanUtils.copyProperties(docket, dto);
		return dto;
	}
	
	public static List<DocketRequest> of(List<Docket> dockets) {
		return dockets.stream().map(DocketRequest::of).collect(Collectors.toList());
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
