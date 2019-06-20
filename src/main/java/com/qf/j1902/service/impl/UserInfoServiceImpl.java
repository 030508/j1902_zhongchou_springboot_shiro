package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.UserInfoMapper;
import com.qf.j1902.pojo.UserInfo;
import com.qf.j1902.service.UserInfoService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Override
    public UserInfo selectUser(String userName) {
        return userInfoMapper.findUserByName(userName) ;
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        userInfoMapper.addUserInfo(userInfo);
    }


    @Override
    public List<UserInfo> findAllByStatus(String status) {
        return userInfoMapper.findAllByStatus(status);
    }

    @Override
    public void deleteUserById(Integer id) {
        userInfoMapper.deleteUserById(id);
    }

    @Override
    public void updateUserById(UserInfo userInfo, Integer userid) {
        userInfoMapper.updateUserById(userInfo,userid);
    }
}
