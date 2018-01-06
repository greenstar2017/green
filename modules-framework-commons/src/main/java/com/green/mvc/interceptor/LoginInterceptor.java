package com.green.mvc.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.green.annotations.NoRequireLogin;
import com.green.auth.utils.LoginUtils;
import com.green.auth.utils.SessionUtils;
import com.green.exception.BusinessAssert;
import com.green.exception.CommonError;

/**
 * 登录拦截
 * 
 * @author yuanhualiang
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            BusinessAssert.error(CommonError.INVALID_REQUEST);
        }

        // 取sid,如没sid，则新生成
        String sid = SessionUtils.getSid(request);
        if (sid == null || sid.length() != 20) {
            SessionUtils.newSid(request,response);
        }

        if (noRequireLogin((HandlerMethod) handler)) {
            return true;
        }

        BusinessAssert.isTrue(LoginUtils.getLoginId(request) > 0 , CommonError.LOGIN_EXPIRE);
        return true;
    }


    private boolean noRequireLogin(HandlerMethod method) {
        return method.getMethod().isAnnotationPresent(NoRequireLogin.class);
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
    }
}