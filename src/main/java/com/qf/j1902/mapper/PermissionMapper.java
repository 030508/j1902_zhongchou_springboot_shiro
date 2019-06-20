package com.qf.j1902.mapper;

import com.qf.j1902.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface PermissionMapper {
    List<Permission> findPermsById(@Param("userName") String userName);
}
