package com.sso.server.util;

import com.sso.server.constant.SsoConstant;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.stream.Stream;

public class CookieUtils {

	private CookieUtils() {
	}

	/**
	 * 生成token
	 */
	public static String createToken(Long id) {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

	/**
	 * 新增cookie
	 */
	public static void addTokenInCookie(String token, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie(SsoConstant.COOKIE_TOKEN_NAME, token);
		cookie.setMaxAge(SsoConstant.COOKIE_TOKEN_TIME_OUT);
		cookie.setPath("/");
		if ("https".equals(request.getScheme())) {
			cookie.setSecure(true);
		}
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}

	/**
	 * 获取cookie
	 */
	public static String getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || StringUtils.isBlank(key)) {
			return null;
		}

		return Stream.of(cookies).filter(c -> key.equals(c.getName())).findAny().map(Cookie::getValue).orElse(null);
	}

	/**
	 * 清除cookie
	 */
	public static void removeCookie(HttpServletResponse response, String key) {
		Cookie newCookie=new Cookie(key,null);
		newCookie.setMaxAge(0);
		newCookie.setPath("/");
		response.addCookie(newCookie);
	}
}
