package com.one.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/")
public class IndexController {

	@Value(value = "${sso.logout}")
	private String logoutUrl;

	/**
	 * 进入首页
	 * http://127.0.0.1:9092/two/index
	 */
	@GetMapping("/index")
	public String index() {
		log.debug("无需登录");
		return "index";
	}

	/**
	 * 权限页面
	 * http://127.0.0.1:9092/two/auth1
	 */
	@GetMapping("/auth1")
	public String auth1() {
		log.debug("需要登录");
		return "auth1";
	}

	/**
	 * 权限页面
	 * http://l127.0.0.1:9092/two/auth2
	 */
	@GetMapping("/auth2")
	public String auth2() {
		log.debug("需要登录");
		return "auth2";
	}

	/**
	 * 登出
	 * http://127.0.0.1:9092/two/logout
	 */
	@GetMapping("/logout")
	public String login(HttpServletRequest request){
		return "redirect:" + logoutUrl + request.getScheme()+"://"+request.getServerName()+":"
				+request.getServerPort()+request.getContextPath()+"/index";
	}

}
