package com.yangm.pojo.system.account.organization;

import java.util.List;

import com.yangm.pojo.base.BaseEntity;

public class Organization extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String code;

    private String orgName;

    private String isleaf;

    private Organization parentid;

    private String status;
    
    private List<Organization> child;
    
   // private List<Car> cars;

    public Organization(Long id, String code, String orgName, String isleaf, Organization parentid, String status) {
        this.id = id;
        this.code = code;
        this.orgName = orgName;
        this.isleaf = isleaf;
        this.parentid = parentid;
        this.status = status;
    }

    public Organization() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getIsleaf() {
        return isleaf == null ? "0" : isleaf.trim();
    }

    public void setIsleaf(String isleaf) {
        this.isleaf = isleaf == null ? "0" : isleaf.trim();
    }

    public Organization getParentid() {
        return parentid;
    }

    public void setParentid(Organization parentid) {
        this.parentid = parentid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? "1" : status.trim();
    }

	public List<Organization> getChild() {
		return child;
	}

	public void setChild(List<Organization> child) {
		this.child = child;
	}

	/*
	 * public List<Car> getCars() { return cars; }
	 * 
	 * public void setCars(List<Car> cars) { this.cars = cars; }
	 */
}
