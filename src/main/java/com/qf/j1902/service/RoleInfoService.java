package com.qf.j1902.service;

import com.qf.j1902.pojo.RoleInfo;

import java.util.List;
import java.util.Set;

public interface RoleInfoService {
    void addRoleById(Integer id);

    void deleteUserRoleById(Integer id);

    Set<RoleInfo> findNotRoleById(int id);

    Set<RoleInfo> findUserRoleById(int id);

    void addUserRoleByUserIdAndRoleId(Integer userid, Integer roleid);

    List<RoleInfo> findAll();
}
