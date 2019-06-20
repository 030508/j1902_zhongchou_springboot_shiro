package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.RoleInfoMapper;
import com.qf.j1902.pojo.RoleInfo;
import com.qf.j1902.service.RoleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleInfoServiceImpl implements RoleInfoService {
    @Resource
    private RoleInfoMapper roleInfoMapper;
    @Override
    public void addRoleById(Integer id) {
        roleInfoMapper.addUserRoleById(id);
    }

    @Override
    public void deleteUserRoleById(Integer id) {
        roleInfoMapper.deleteUserRoleById(id);
    }

    @Override
    public  Set<RoleInfo> findNotRoleById(int id) {
        List<RoleInfo> roleById = roleInfoMapper.findNotRoleById(id);
        Set<RoleInfo> roleInfos = new HashSet<>();
        Set<RoleInfo> userRoleById = findUserRoleById(id);
        for (RoleInfo u:roleById){
            if (userRoleById.contains(u)){
                continue;
            }
            roleInfos.add(u);
        }
        return  roleInfos;
    }

    @Override
    public Set<RoleInfo> findUserRoleById(int id) {
        List<RoleInfo> userRoleById = roleInfoMapper.findUserRoleById(id);
        Set<RoleInfo> roleInfos = new HashSet<>();
        for (RoleInfo u:userRoleById){
            roleInfos.add(u);
        }
        return  roleInfos;
    }

    @Override
    public void addUserRoleByUserIdAndRoleId(Integer userid, Integer roleid) {
        roleInfoMapper.addUserRoleByUserIdAndRoleId(userid,roleid);
    }

    @Override
    public List<RoleInfo> findAll() {
        return roleInfoMapper.fidAll();
    }
}
