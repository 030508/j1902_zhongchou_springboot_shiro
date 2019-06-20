package com.qf.j1902.shiro;

import com.qf.j1902.pojo.Permission;
import com.qf.j1902.pojo.UserInfo;
import com.qf.j1902.service.PermissionService;
import com.qf.j1902.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PermissionService permissionService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户名
        String principal = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名查询权限信息
        Set<Permission> permissionSet = permissionService.selecctpermsByname(principal);
        Set<String > perms = new HashSet<>();
        if (permissionSet !=null ){
            for (Permission p: permissionSet){
                perms.add(p.getPerName());
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();//权限设置
        authorizationInfo.setStringPermissions(perms);//添加权限
        return authorizationInfo;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        UserInfo userInfo = userInfoService.selectUser(username);
        SimpleAuthenticationInfo authenticationInfo=null;
        if (userInfo !=null){
            authenticationInfo = new SimpleAuthenticationInfo(username,userInfo.getPassword(),this.getName());
        }
        return authenticationInfo;
    }
}
