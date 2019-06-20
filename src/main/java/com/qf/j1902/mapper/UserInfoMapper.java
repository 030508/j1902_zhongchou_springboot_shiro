package com.qf.j1902.mapper;

import com.qf.j1902.pojo.UserInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    UserInfo findUserByName(@Param("userName") String userName);

    void addUserInfo(UserInfo userInfo);

    List<UserInfo> findAllByStatus(String status);

    void deleteUserById(Integer id);

    void updateUserById(@Param("userInfo") UserInfo userInfo,@Param("uid") Integer uid);
}
