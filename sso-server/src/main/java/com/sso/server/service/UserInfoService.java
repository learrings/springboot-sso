package com.sso.server.service;

import com.sso.server.domain.UserInfo;

/**
 * <p>Description:[用户interface]</p>
 * Create on 2019/7/6
 *
 * @author learrings
 */
public interface UserInfoService {

	UserInfo getByLogin(String name, String password);
}
