/**
 * 
 */
package com.green.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.annotations.NoRequireLogin;
import com.green.annotations.UserId;
import com.green.auth.utils.LoginUtils;
import com.green.common.EnumTool;
import com.green.constants.AccountTypeEnum;
import com.green.dto.LoginForm;
import com.green.entity.UserAccount;
import com.green.response.RestObject;
import com.green.service.LoginService;

/**
 * @author yuanhualiang
 *
 */
@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private LoginService loginService;

	/**
	 * 登录页面跳转
	 * 
	 * @return
	 */
	@NoRequireLogin
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 * 后台管理首页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@NoRequireLogin
	@RequestMapping(value = "/console", method = RequestMethod.GET)
	public String console(HttpServletRequest request, Model model, @UserId long userId) {
		if(userId == -1) {
			return "redirect:login.html";
		}
		return "consoleIndex";
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @param loginForm
	 * @return
	 */
	@NoRequireLogin
	@ResponseBody
	@RequestMapping("/userLogin")
	public RestObject login(HttpServletRequest request,
			HttpServletResponse response, LoginForm loginForm) {
		UserAccount userAccount = loginService.doLogin(request, response,
				loginForm);
		Map<String, Object> resultData = new HashMap<>();
		Map<String, Object> baseData = new HashMap<>();
		resultData.put("baseData", baseData);
		if (null != userAccount) {
			resultData.put("userAccount", userAccount);
			baseData.put("accountTypeEnum", EnumTool.getEnumPropertyMap(AccountTypeEnum.class));
			return RestObject.newOk("登录成功", resultData);
		} else {
			return RestObject.newError("账号或者密码不正确");
		}
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 */
	@NoRequireLogin
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
			HttpServletResponse response, @UserId long userId) {
		LoginUtils.logout(request, response, userId);
		return "login";
	}

	@ResponseBody
	@RequestMapping("/testNeedLogin")
	public RestObject testNeedLogin(HttpServletRequest request,
			HttpServletResponse response, UserAccount userAccount) {
		return RestObject.newOk("testNeedLogin", userAccount);
	}

	@NoRequireLogin
	@ResponseBody
	@RequestMapping("/testNoNeedLogin")
	public RestObject testNoNeedLogin(HttpServletRequest request,
			HttpServletResponse response) {
		return RestObject.newOk("testNoNeedLogin");
	}

}
