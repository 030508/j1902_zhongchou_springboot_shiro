package com.qf.j1902.config;

import com.qf.j1902.shiro.MyRealm;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //创建自定义Realm对象
    @Bean(name = "myRealm")
    public MyRealm myRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher); //设置凭证管理器
        return  myRealm;
    }

    //创建安全管理器（认证）
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager( @Qualifier("myRealm") MyRealm myRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }
    //创建安全管理器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();  //创建shiro过滤器工厂容器
        factoryBean.setSecurityManager(securityManager);//设置安全管理器
        factoryBean.setLoginUrl("/login");//设置登录页
        factoryBean.setUnauthorizedUrl("/unauth");//无权访问时候返回此页面
        Map<String, String> map = new HashMap<>(); //创建hashMap 用于存储权限
        map.put("/manager/**","authc");//只有登陆才可以访问的页面
        factoryBean.setFilterChainDefinitionMap(map);//设置拦截权限
        return  factoryBean;
    }
    //权限注解验证模式
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        return proxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor( DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }
    //MD5加密
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher ( ){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(); //创建凭证匹配器
        matcher.setHashAlgorithmName("MD5"); //设置算法（名称）
        matcher.setHashIterations(1024);//设置加密次数（加密迭代次数）
        matcher.setStoredCredentialsHexEncoded(true); //设置16进制字符串
        return matcher;
    }



}
