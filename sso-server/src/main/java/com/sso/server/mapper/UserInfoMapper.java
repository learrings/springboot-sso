package com.sso.server.mapper;


import com.sso.server.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>Description:[用户mapper]</p>
 * Create on 2019/7/6
 *
 * @author learrings
 */
@Mapper
@Repository
public interface UserInfoMapper {

	UserInfo getByLogin(@Param("name") String name, @Param("password")String password);
}
