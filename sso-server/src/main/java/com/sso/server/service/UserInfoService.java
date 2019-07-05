package com.sso.server.service;

import com.sso.server.domain.UserInfo;

public interface UserInfoService {

	UserInfo getByLogin(String name, String password);
}
