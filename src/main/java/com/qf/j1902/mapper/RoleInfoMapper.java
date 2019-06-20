package com.qf.j1902.mapper;

import com.qf.j1902.pojo.RoleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleInfoMapper {
    List<RoleInfo> fidAll();
    List<RoleInfo> findNotRoleById(Integer id);
    List<RoleInfo>  findUserRoleById( Integer id);
    void insertRoleByRoleInfo(RoleInfo roleInfo); //添加角色信息
    void deleteRoleById(Integer id);
    void updateRoleById(RoleInfo roleInfo,Integer id);

    void addUserRoleById(@Param("id") Integer id); //添加 用户—角色 关系

    void deleteUserRoleById(@Param("id")Integer id);

    void addUserRoleByUserIdAndRoleId(@Param("userid") Integer userid,@Param("roleid") Integer roleid);
}
