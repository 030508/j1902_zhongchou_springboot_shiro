package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.RealNameMapper;
import com.qf.j1902.pojo.RealName;
import com.qf.j1902.service.RealNameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RealNameServiceImpl implements RealNameService {
    @Resource
    private RealNameMapper realNameMapper;
    @Override
    public void addRealName(RealName realName) {
        realNameMapper.createRealByRealName(realName);
    }

    @Override
    public RealName selectRealNameByUname(String uname) {
        return realNameMapper.selectRealNameByUname(uname);
    }

    @Override
    public List<RealName> selectAllByCode(String ss) {
        return realNameMapper.selectAllByCode(ss);
    }

    @Override
    public boolean updateCodeByUserName(String code, String yijian,String userName) {
        return realNameMapper.updateCodeByUserName( code,yijian, userName) ;
    }

    @Override
    public void aaddUserRole(Integer userid, Integer i) {
        realNameMapper.addUserRole(userid,i);
    }


}
