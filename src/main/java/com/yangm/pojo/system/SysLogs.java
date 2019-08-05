package com.yangm.pojo.system;

import com.yangm.pojo.base.BaseEntity;

public class SysLogs extends BaseEntity<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

	private String module;

	private Boolean flag;

	private String remark;

	private String createTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
