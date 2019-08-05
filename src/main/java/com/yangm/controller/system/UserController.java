package com.yangm.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yangm.annotation.LogAnnotation;
import com.yangm.base.ResponseInfo;
import com.yangm.controller.base.BaseController;
import com.yangm.pojo.system.User;
import com.yangm.service.system.UserService;
import com.yangm.utils.UserUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 用户相关接口
 */
@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private UserService userService;

	// 首页获取登录用户信息
	@RequestMapping("/current")
	@ResponseBody
	public User currentUser() {
		return UserUtil.getCurrentUser();
	}

	// 跳转至用户界面
	@RequestMapping("/findUserList")
	@RequiresPermissions("users:findUserList")
	public String toUserList() {
		return "/system/user/user_list";
	}

	// 按条件查询
	@RequestMapping("/findUsersByCondition")
	@RequiresPermissions("users:findUserList")
	@ResponseBody
	public String findUsersByCondition(User users) {
		return userService.findUsersByCondition(users);
	}//

	// 跳转修改密码界面
	@RequestMapping("/toChangePassword")
	public String toChangePassword() {
		return "/system/user/toChangePassword";
	}

	@LogAnnotation
	@ApiOperation(value = "修改密码")
	@RequestMapping("/changePassword")
	@ResponseBody
	public ResponseInfo<User> changePassword(String loginName, String oldPassword, String newPassword,
			String configNewPassword) {

		if (loginName != null && loginName.trim().equals("")) {
			throw new IllegalArgumentException("登录名为空");
		}

		if (oldPassword != null && oldPassword.trim().equals("")) {
			throw new IllegalArgumentException("原密码不能为空");
		}

		if (newPassword != null && newPassword.trim().equals("")) {
			throw new IllegalArgumentException("新密码不能为空");
		}

		if (oldPassword.trim().equals(configNewPassword.trim())) {
			throw new IllegalArgumentException("两次输入的密码不一致");
		}

		userService.changePassword(loginName.trim(), oldPassword.trim(), newPassword.trim());

		return ResponseInfo.createBySuccessMessage("修改密码成功");
	}
}
