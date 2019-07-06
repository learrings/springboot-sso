package com.sso.server.controller;

import com.sso.server.constant.SsoConstant;
import com.sso.server.domain.UserInfo;
import com.sso.server.util.CookieUtils;
import com.sso.server.util.RedisUtil;
import com.sso.server.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sso.common.entity.ExecuteResult;
import com.sso.common.constant.GlobalConstant;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
@RequestMapping("/")
public class LoginController {

	@Resource
	private UserInfoService userInfoService;
	@Resource
	private RedisUtil redisUtil;

	/**
	 * 普通登录(sso登录页面的请求)
	 */
	@PostMapping("login")
	public String login(String serverUrl, String name, String password, Model model,
						HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		UserInfo userInfo = userInfoService.getByLogin(name, password);
		if (userInfo == null) {
			model.addAttribute("result", ExecuteResult.ok(serverUrl, SsoConstant.LOGIN_ERROR));
			return SsoConstant.LOGIN_PAGE;
		}

		String token = CookieUtils.getCookie(request, SsoConstant.COOKIE_TOKEN_NAME);
		if(StringUtils.isBlank(token)){
			token = CookieUtils.createToken(userInfo.getId());
		}

		CookieUtils.addTokenInCookie(token, request, response);
		redisUtil.set(token, userInfo.getId(), GlobalConstant.SSO_TOKEN_TIME_OUT);
		return "redirect:" + backUrl(serverUrl, token);
	}

	/**
	 * 直接登录（应用端无token，请求sso获取）
	 * http://127.0.0.1:9090/sso/login?serverUrl=http://localhost:9091/one/auth1
	 */
	@GetMapping("login")
	public String login(String serverUrl, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		String token = CookieUtils.getCookie(request, SsoConstant.COOKIE_TOKEN_NAME);
		if (StringUtils.isBlank(token)) {
			model.addAttribute("result", ExecuteResult.ok(serverUrl, SsoConstant.LOGIN_UN));
			return SsoConstant.LOGIN_PAGE;
		}

		return "redirect:" + backUrl(serverUrl, token);
	}

	/**
	 * 退出系统，跳转到服务端的自定义页（无需登录页）
	 * http://127.0.0.1:9090/sso/logout?logoutUrl=http://localhost:9091/one/index
	 */
	@GetMapping("logout")
	public String logout(String serverUrl, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String token = CookieUtils.getCookie(request, SsoConstant.COOKIE_TOKEN_NAME);
		CookieUtils.removeCookie(response, SsoConstant.COOKIE_TOKEN_NAME);
		redisUtil.del(token);
		return "redirect:" + backUrl(serverUrl, null);
	}

	private String backUrl(String serverUrl, String token) throws UnsupportedEncodingException {
		serverUrl = URLDecoder.decode(serverUrl, GlobalConstant.UTF8);
		if (StringUtils.isBlank(token)) {
			return serverUrl;
		}
		return serverUrl + (serverUrl.indexOf("?") > 0 ? "&" : "?") + GlobalConstant.SSO_TOKEN_NAME + "=" + token;
	}
}
