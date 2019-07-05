package com.sso.server.mapper;


import com.sso.server.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserInfoMapper {

	UserInfo getByLogin(@Param("name") String name, @Param("password")String password);
}
