package com.sso.server.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Description:[用户信息</p>
 * Create on 2019/7/6
 *
 * @author learrings
 */
@Getter
@Setter
public class UserInfo implements Serializable {
	private Long id;
	private String name;
	private String password;
	private String loginIp;
}
