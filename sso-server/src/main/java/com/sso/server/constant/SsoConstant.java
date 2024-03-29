package com.sso.server.constant;

/**
 * <p>Description:[SSO全局常量]</p>
 * Create on 2019/7/6
 *
 * @author learrings
 */
public class SsoConstant {

	public static String COOKIE_TOKEN_NAME = "token";
	public static int COOKIE_TOKEN_TIME_OUT = 3600;
	public static String LOGIN_PAGE = "login";

	public static String LOGIN_RESPONSE = "result";
	public static String LOGIN_UN = "用户未登录，跳转登录页";
	public static String LOGIN_ERROR = "用户登录失败，跳转登录页";

	public static String PATH = "/";

	public static String SPLIT_LINE="-";
}
