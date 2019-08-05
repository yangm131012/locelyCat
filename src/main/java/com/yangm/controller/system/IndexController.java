package com.yangm.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("index")
@Controller
public class IndexController {

	@RequestMapping("/welcome")
	public String toLogin() {
		return "welcome";
	}
}
