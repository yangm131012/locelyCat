package com.yangm.controller.system;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yangm.dao.system.PermissionDao;
import com.yangm.pojo.system.Permission;
import com.yangm.pojo.system.User;
import com.yangm.utils.UserUtil;


/**
 * 权限相关接口
 */
@RestController
@RequestMapping("/permissions")
public class PermissionController {

	@Autowired
	private PermissionDao permissionDao;
	@RequestMapping("/current")
	public List<Permission> permissionsCurrent() {
		List<Permission> list = UserUtil.getCurrentPermissions();
		if (list == null) {
			User user = UserUtil.getCurrentUser();
			list = permissionDao.listByUserId(user.getSerial());
			UserUtil.setPermissionSession(list);
		}
		final List<Permission> permissions = list.stream().filter(l -> l.getType().equals(1))
				.collect(Collectors.toList());

		List<Permission> firstLevel = permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
		firstLevel.parallelStream().forEach(p -> {
			setChild(p, permissions);
		});

		return firstLevel;
	}
	
	/**
	 * 设置子元素
	 * 2018.06.09
	 *
	 * @param p
	 * @param permissions
	 */
	private void setChild(Permission p, List<Permission> permissions) {
		List<Permission> child = permissions.parallelStream().filter(a -> a.getParentId().equals(p.getId())).collect(Collectors.toList());
		p.setChild(child);
		if (!CollectionUtils.isEmpty(child)) {
			child.parallelStream().forEach(c -> {
				//递归设置子元素，多级菜单支持
				setChild(c, permissions);
			});
		}
	}
}
