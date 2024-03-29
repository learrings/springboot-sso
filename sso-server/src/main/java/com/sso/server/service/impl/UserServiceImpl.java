package com.sso.server.service.impl;

import com.sso.server.domain.UserInfo;
import com.sso.server.mapper.UserInfoMapper;
import com.sso.server.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>Description:[用户service]</p>
 * Create on 2019/7/6
 *
 * @author learrings
 */
@Slf4j
@Service
public class UserServiceImpl implements UserInfoService {

	@Resource
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfo getByLogin(String name, String password) {
		return userInfoMapper.getByLogin(name, password);
	}
}
