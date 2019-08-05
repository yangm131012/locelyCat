package com.yangm.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yangm.pojo.system.User;

public interface UserDao {
int saveUser(User user);
	
	int updateUser(User user);
	
	int deleteUserById(Long serial);

	User getUserById(Long serial);

	User getUserByLoginName(String loginName);
	
	User getUserByUserName(String userName);
	
	User checkLoginName(String loginName, Long serial);
	
	List<User> findUserByCondition(User user);
	
	int saveUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

	int deleteUserRole(Long serial);
}