package com.one.Interceptor;

import com.one.util.RedisUtil;
import com.sso.common.constant.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class SSOInterceptor implements HandlerInterceptor {

	@Value(value = "${sso.open}")
	private boolean openSso;
	@Value(value = "${sso.login}")
	private String loginUrl;

	@Resource
	private RedisUtil redisUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!openSso) {
			return true;
		}

//		UserInfo userInfo = (UserInfo) redisUtil.get(request.getHeader(GlobalConstant.SSO_TOKEN_NAME));
		Long id = (Long) redisUtil.get(request.getParameter(GlobalConstant.SSO_TOKEN_NAME));
		if (id == null) {
			response.sendRedirect(loginUrl + request.getRequestURL());
		}
		return true;
	}
}
