package com.yangm.controller.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yangm.annotation.LogAnnotation;
import com.yangm.base.ResponseInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "登陆")
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private ServerProperties serverProperties;
    
	
	@LogAnnotation
	@ApiOperation(value = "web端登陆")
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}
	
    @RequestMapping("/login")
    @ResponseBody
    public ResponseInfo<?> login(String username, String password, String code)
    {
		String validateCode = (String)SecurityUtils.getSubject().getSession().getAttribute("confirmCode");

		if (validateCode == null) {
			throw new UnknownAccountException("验证码无效，请刷新");
		}
		
		if (!validateCode.trim().equals(code)) {
			throw new UnknownAccountException("验证码不正确");
		}

		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
		SecurityUtils.getSubject().login(usernamePasswordToken);
		// 设置shiro的session过期时间
		SecurityUtils.getSubject().getSession().setTimeout(serverProperties.getServlet().getSession().getTimeout().toMillis());
		// 设置验证码为空
		SecurityUtils.getSubject().getSession().setAttribute("confirmCode", null);
		
		return ResponseInfo.createBySuccessMessage("success");
    }
    
    /**
             * 产生图片验证码
     * @return 验证码字符串
     */
    @RequestMapping("/validNumGenerate")
    // @ResponseBody
    public String validNumGenerate(HttpServletRequest request, HttpServletResponse response){
		int width = 80;
		int height = 34;

		// 1. 创建一张内存图片
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 2.获得绘图对象
		Graphics graphics = bufferedImage.getGraphics();

		// 3、绘制背景颜色
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);

		// 4、输出验证码内容
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("Times New Roman", Font.PLAIN, 28));

		// 随机输出4个字符
		Graphics2D graphics2d = (Graphics2D) graphics;
		String s = "0123456789";
		Random random = new Random();
		//session中要用到
		String msg="";
		int x = 5;
		for (int i = 0; i < 4; i++) {
			int index = random.nextInt(10);
			String content = String.valueOf(s.charAt(index));
			msg+=content;
			// 让字体扭曲
			graphics2d.drawString(content, x, 28);
			x += 15;
		}
		
		// 将验证码放入域中
		request.getSession().setAttribute("confirmCode", msg);

		// 释放资源
		graphics.dispose();

		// 图片输出 ImageIO
		try {
			ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }    
}
