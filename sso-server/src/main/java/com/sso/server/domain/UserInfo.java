package com.sso.server.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserInfo implements Serializable {
	private Long id;
	private String name;
	private String password;
	private String loginIp;
}
