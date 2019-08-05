package com.yangm.service.system;

import java.util.List;

import com.yangm.pojo.system.User;

public class UserDto extends User{

	private static final long serialVersionUID = -184009306207076712L;

	private List<Long> roleIds;

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}
}
