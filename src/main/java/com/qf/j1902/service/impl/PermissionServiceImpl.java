package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.PermissionMapper;
import com.qf.j1902.pojo.Permission;
import com.qf.j1902.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Override
    public Set<Permission> selecctpermsByname(String name) {
        List<Permission> permsById = permissionMapper.findPermsById(name);
        Set<Permission> permis= new HashSet<>();
        for(Permission p: permsById){
            permis.add(p);
        }
        return permis;
    }
}
