package com.yangm.pojo.system;

import com.yangm.pojo.base.BaseEntity;

public class Role extends BaseEntity<Long>{

private static final long serialVersionUID = -3802292814767103648L;
	
	private Long id;

	private String name;

	private String description;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
