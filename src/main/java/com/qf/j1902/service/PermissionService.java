package com.qf.j1902.service;

import com.qf.j1902.pojo.Permission;

import java.util.Set;

public interface PermissionService {
    Set<Permission> selecctpermsByname(String name);
}
