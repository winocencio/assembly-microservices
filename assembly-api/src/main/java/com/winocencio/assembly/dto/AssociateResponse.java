package com.winocencio.assembly.dto;

import com.winocencio.assembly.model.VotePermissionEnum;

public class AssociateResponse {

	private VotePermissionEnum status;

	public VotePermissionEnum getStatus() {
		return status;
	}

	public void setStatus(VotePermissionEnum status) {
		this.status = status;
	}

}
