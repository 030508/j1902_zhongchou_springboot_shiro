package com.qf.j1902.mapper;

import com.qf.j1902.pojo.RealName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RealNameMapper {
    void createRealByRealName(RealName realName);

    RealName selectRealNameByUname(@Param("uname") String uname);

    List<RealName> selectAllByCode(@Param("code") String ss);

    boolean updateCodeByUserName(String code,String AuditMind ,String userName);

    void addUserRole(Integer userid, Integer roleid);
}
