/**
 * 
 */
package com.green.action.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.annotations.NoRequireLogin;
import com.green.auth.utils.LoginUtils;
import com.green.response.RestObject;

/**
 * @author yuanhualiang
 *
 */
@Controller
@RequestMapping("/demo")
public class TestController {

	@NoRequireLogin
	@ResponseBody
	@RequestMapping("/login")
	public RestObject login(HttpServletRequest request,
			HttpServletResponse response) {
		
		LoginUtils.setLoginId(request, response, 1);
		return RestObject.newOk("登录成功");
	}
	
	@ResponseBody
	@RequestMapping("/logout")
	public RestObject logout(HttpServletRequest request,
			HttpServletResponse response) {
		LoginUtils.logout(request, response, 1);
		return RestObject.newOk("登出成功");
	}
	
	@ResponseBody
	@RequestMapping("/testNeedLogin")
	public RestObject testNeedLogin(HttpServletRequest request,
			HttpServletResponse response) {
		return RestObject.newOk("testNeedLogin");
	}
	
	@NoRequireLogin
	@ResponseBody
	@RequestMapping("/testNoNeedLogin")
	public RestObject testNoNeedLogin(HttpServletRequest request,
			HttpServletResponse response) {
		return RestObject.newOk("testNoNeedLogin");
	}
}
