package com.yangm.constants;

public interface UserConstants {

	/** 加密次数 */
	int HASH_ITERATIONS = 3;

	String LOGIN_USER = "login_user";

	String USER_PERMISSIONS = "user_permissions";

	/** 登陆token(nginx中默认header无视下划线) */
	String LOGIN_TOKEN = "login-token";

	/**
	 * 重置密码
	 */
	String RESET_PASSWORD = "000000";
}
