package com.yangm.controller.infomation;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yangm.controller.base.BaseController;
import com.yangm.pojo.infomation.Cat;
import com.yangm.service.infomation.CatService;
/**
 * 猫咪信息相关接口
 */
@Controller
@RequestMapping("/cats")
public class CatController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger("adminLogger");
	@Autowired
	private CatService catService;
	
	//跳转至猫咪信息页面
	@RequestMapping("/findCatList")
	@RequiresPermissions("cats:findCatList")
	public String toUserList() {
		return "/infomation/cat_list";
	}
	
	// 按条件查询
	@RequestMapping("/findCatsByCondition")
	@RequiresPermissions("cats:findCatList")
	@ResponseBody
	public String findCatsByCondition(Cat cats) {
		return catService.findCatsByCondition(cats);
	}
}
