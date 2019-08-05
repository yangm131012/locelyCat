package com.yangm.service.system;

import com.github.pagehelper.PageInfo;
import com.yangm.pojo.system.User;

public interface UserService {

	User saveUser(User user);

	User updateUser(User user);

	String passwordEncoder(String credentials, String salt);

	User getUserByLoginName(String loginName);

	User getUserByUserName(String userName);

	void changePassword(String username, String oldPassword, String newPassword);

	String findUsersByCondition(User users);

	PageInfo<User> findUserByAttr(User users, int pageNum, int pageSize);

	User getUserById(Long serial);

	User checkLoginName(String username, Long serial);

	int deleteUserById(Long serial);

	int resetPassword(Long serial);
}
