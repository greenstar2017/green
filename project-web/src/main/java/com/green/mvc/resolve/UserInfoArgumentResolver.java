package com.green.mvc.resolve;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import com.green.annotations.UserId;
import com.green.annotations.UserInfo;
import com.green.auth.utils.LoginUtils;
import com.green.service.LoginService;

/**
 * 注入UserInfo
 * 
 * @author yuanhualiang
 */
@Component
public class UserInfoArgumentResolver implements WebArgumentResolver {

	@Autowired
	private LoginService loginService;
	
	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {

		long userId = LoginUtils.getLoginId((HttpServletRequest) webRequest
				.getNativeRequest());
		boolean isLogin = userId > 0;

		if (methodParameter.hasParameterAnnotation(UserId.class)) {
			return isLogin ? userId : -1;
		}

		if (UserInfo.class.equals(methodParameter.getParameterType())) {
			return isLogin ? loginService.getUserInfo(userId) : null;
		}

		return UNRESOLVED;
	}
}