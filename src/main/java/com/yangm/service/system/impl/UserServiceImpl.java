package com.yangm.service.system.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangm.constants.UserConstants;
import com.yangm.dao.system.RoleDao;
import com.yangm.dao.system.UserDao;
import com.yangm.pojo.system.Role;
import com.yangm.pojo.system.User;
import com.yangm.service.system.UserService;
import com.yangm.utils.GeneralUtils;
import com.yangm.utils.UserUtil;
@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public User saveUser(User user) {
		
		user.setSalt(DigestUtils
				.md5Hex(UUID.randomUUID().toString() + System.currentTimeMillis() + UUID.randomUUID().toString()));
		user.setPassword(passwordEncoder(user.getPassword(), user.getSalt()));
		userDao.saveUser(user);
		saveUserRoles(user.getSerial(), user.getRoleId());

		log.debug("新增用户{}", user.getUserName());
		return user;
	}

	private void saveUserRoles(Long userId, Long roleId) {
		if (GeneralUtils.isNotNullOrZero(roleId)) {
			userDao.deleteUserRole(userId);
			userDao.saveUserRole(userId, roleId);
		}
	}

	@Override
	public String passwordEncoder(String credentials, String salt) {
		Object object = new SimpleHash("MD5", credentials, salt, UserConstants.HASH_ITERATIONS);
		return object.toString();
	}

	@Override
	public User getUserByLoginName(String loginName) {
		return userDao.getUserByLoginName(loginName);
	}

	@Override
	public void changePassword(String loginName, String oldPassword, String newPassword) {
		// 从session获取用户名
		User currentUser = UserUtil.getCurrentUser();
		if (currentUser == null) {
			throw new IllegalArgumentException("获取不到用户，请重新登录");
		} else {
			// 判断session登录名和前端传来的用户名是否一致
			if (currentUser.getLoginName() != null && currentUser.getLoginName().equals(loginName)) {
				User u = userDao.getUserByLoginName(loginName);
				if (u == null) {
					throw new IllegalArgumentException("登录不存在");
				}
				
				if (!u.getPassword().equals(passwordEncoder(oldPassword, u.getSalt()))) {
					throw new IllegalArgumentException("原密码错误");
				}
				
				u.setPassword(passwordEncoder(newPassword, u.getSalt()));
				
				userDao.updateUser(u);
				
				log.debug("修改{}的密码", u.getUserName());
			} else {
				throw new IllegalArgumentException("未查到用户");
			}
		}
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		userDao.updateUser(user);
		if (GeneralUtils.isNotNullOrZero(user.getRoleId())) {
			saveUserRoles(user.getSerial(), user.getRoleId());
		}
		updateUserSession(user.getSerial());
		return user;
	}

	private void updateUserSession(Long serial) {
		User current = UserUtil.getCurrentUser();
		if (current.getSerial().equals(serial)) {
			User user = userDao.getUserById(serial);
			UserUtil.setUserSession(user);
		}
	}

	@Override
	public String findUsersByCondition(User user) {
		PageHelper.startPage(user.getPageNumber(), user.getPageSize());
		List<User> pageInfo = userDao.findUserByCondition(user);
		PageInfo<User> pageInfos = new PageInfo<>(pageInfo);
		JSONObject result = new JSONObject();
		result.put("rows",pageInfo);
		result.put("total", pageInfos.getTotal());	
		log.debug(result.toJSONString());
		return result.toJSONString();
	}

	@Override
	public PageInfo<User> findUserByAttr(User user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> userList = userDao.findUserByCondition(user);
		return new PageInfo<User>(userList);
	}

	@Override
	public User getUserById(Long serial) {
		
		User user = userDao.getUserById(serial);
		
		//  用户类型 0：会员 1：管理员
		if(user.getUserType().equals("1")) {
			List<Role> roles = roleDao.listByUserId(serial);
			
			user.setRoleId(roles.get(0).getId());
			
			user.setRoleName(roles.get(0).getName());
		}
		
		return user;
	}

	@Override
	public User checkLoginName(String loginName, Long serial) {
		return userDao.checkLoginName(loginName, serial);
	}


	@Override
	public int deleteUserById(Long serial) {
		userDao.deleteUserRole(serial);
		return userDao.deleteUserById(serial);
	}

	@Override
	public int resetPassword(Long serial) {
		User user = userDao.getUserById(serial);
		if (user == null) {
			return 0;
		}
		user.setPassword(passwordEncoder(UserConstants.RESET_PASSWORD, user.getSalt()));
		
		return userDao.updateUser(user);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}


}
