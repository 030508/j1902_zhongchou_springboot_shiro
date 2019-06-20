package com.qf.j1902.service;

import com.qf.j1902.pojo.RealName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RealNameService {



    void addRealName(RealName realName);

    RealName selectRealNameByUname(String uname);

    List<RealName> selectAllByCode( String ss);

    boolean updateCodeByUserName(String code, String yijian,String userName);



    void aaddUserRole(Integer userid, Integer i);
}
