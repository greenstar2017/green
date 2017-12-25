/**
 * 
 */
package com.green.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.response.RestObject;

/**
 * @author yuanhualiang
 *
 */
public interface LoginService {

	/**
	 * 登录
	 */
	String doLogin(HttpServletRequest request, HttpServletResponse response,
			long memberId);

	/**
	 * @param userId
	 * @return
	 */
	Object getUserInfo(long userId);
}
