package com.two.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
*  用户信息
*
* @author  learrtins
*/
@Getter
@Setter
public class UserInfo implements Serializable {
	private Long id;
	private String name;
	private String password;
}
