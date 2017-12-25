package com.green.auth.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.green.auth.constants.LoginConstants;
import com.green.constants.PropertiesContants;
import com.green.response.RestObject;
import com.green.utils.mvc.CookieUtils;

/**
 * 登录工具类
 * 
 * @author yuanhualiang
 */
public class LoginUtils {

	private static Logger logger = LoggerFactory.getLogger(LoginUtils.class);
	
	public static String setLoginId(HttpServletRequest request,
			HttpServletResponse response, long userId) {
		// 将登录态写入cookies
		String tokenString = TokenUtils.generateToken(
				LoginConstants.TOKEN_SIGN_SECRET, userId);
		CookieUtils.addCookie(response, LoginConstants.TOKEN_NAME, tokenString,
				PropertiesContants.that.getCookieBaseDomain(), "/", Integer.MAX_VALUE);

		return tokenString;
	}

	public static RestObject logout(HttpServletRequest request,
			HttpServletResponse response, long userId) {
		logger.debug("logout user :{}", getLoginId(request));

		CookieUtils.removeCookie(response, LoginConstants.TOKEN_NAME, "/");
		return RestObject.newOk("登录退出成功");
	}

	public static long getLoginId(HttpServletRequest request) {
		return TokenUtils.parseToken(CookieUtils.getCookie(request,
				LoginConstants.TOKEN_NAME));
	}
}
