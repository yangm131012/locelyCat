package com.yangm.pojo.system;

import java.util.HashSet;
import java.util.Set;

import com.yangm.pojo.base.BaseEntity;

public class User extends BaseEntity<Long>{
	private static final long serialVersionUID = -6525908145032868837L;

	/**
	 * 主键
	 */
	private Long serial;

	/**
	 * 用户类型 0：会员 1：管理员
	 */
	private String userType;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 公司名称
	 */
	private String companyName;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 邮箱
	 */
	private String mail;

	/**
	 * 手机号
	 */
	private String phoneNumber;

	/**
	 * 公司简介
	 */
	private String companyIntroduce;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 电话
	 */
	private String phone;

	/**
	 * 状态 0：未启用 1：已启用 2：已停用 3：已删除
	 */
	private String status;

	/**
	 * 注册时间
	 */
	private String createTime;

	/**
	 * 用户拥有的角色
	 */
	private Set<Role> roles = new HashSet<Role>();

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	public Long getSerial() {
		return serial;
	}

	public void setSerial(Long serial) {
		this.serial = serial;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompanyIntroduce() {
		return companyIntroduce;
	}

	public void setCompanyIntroduce(String companyIntroduce) {
		this.companyIntroduce = companyIntroduce;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public interface Status {
		String DISABLED = "0";
		String VALID = "1";
		String LOCKED = "2";
	}
	
}