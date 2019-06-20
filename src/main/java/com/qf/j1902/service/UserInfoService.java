package com.qf.j1902.service;

import com.qf.j1902.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {
    UserInfo selectUser(String userName);

    void addUserInfo(UserInfo userInfo);

    List<UserInfo> findAllByStatus(String status);

    void deleteUserById(Integer id);

    void updateUserById(UserInfo userInfo, Integer userid);
}
