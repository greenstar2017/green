/**
 * 
 */
package com.green.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.green.service.LoginService;

/**
 * @author yuanhualiang
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	private Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Override
	public String doLogin(HttpServletRequest request,
			HttpServletResponse response, long memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getUserInfo(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
