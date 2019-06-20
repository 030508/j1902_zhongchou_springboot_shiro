package com.qf.j1902.controller;

import com.qf.j1902.pojo.UserInfo;
import com.qf.j1902.service.UserInfoService;
import com.qf.j1902.utils.Sessionkey;
import com.qf.j1902.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class LoginAanRegController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("unauth")
    public  String unauth(){
        return "redirect:unauth";
    }
    @GetMapping("login")
    public  String login(){
        return "login";
    }
    @GetMapping("reg")
    public  String reg(){
        return "reg";
    }
    @GetMapping("denglu")
    public  String denglu(UserVo userVo, HttpSession session, Model model){

        //判断用户名密码不为空
        if (userVo.getUname() == null ||userVo.getUname() == "" ||
                userVo.getPassword() == null ||userVo.getPassword() == ""){
            model.addAttribute("uu","1");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject(); //获取安全主体（subject）
        UsernamePasswordToken token = new UsernamePasswordToken(userVo.getUname(),userVo.getPassword());//获取令牌
        try {
            subject.login(token);//密码校验
            if (subject.isAuthenticated()){
                UserInfo userInfo = userInfoService.selectUser(userVo.getUname());//通过用户名查询用户信息
                if (userInfo.getStatus().equals(userVo.getCustomer())){//判断用户信息身份类型是否正确
                    userVo.setPassword(null);//清除密码
                    session.setAttribute(Sessionkey.USER,userVo);//把用户信息存储在session中
                    session.setAttribute(Sessionkey.USER_NAME,userVo.getUname());//单独存储用户名在session中
                    //判断是会员用户还是管理员用户返回对应主页面
                    return  "会员".equals(userVo.getCustomer()) ?  "redirect:/manager/member":"redirect:/manager/main";
                }
            }
        } catch (AuthenticationException e) {
        }
        model.addAttribute("uu","2");
        return "login";
    }

    @GetMapping("zhuce")
    public  String zhuce(UserVo userVo,Model model){
        //邮箱格式正则表达式，用于邮箱格式验证
        String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
       if (!userVo.getEmail().matches(regex)){
           model.addAttribute("uu",1);
           return "reg";
       }
        if(userVo.getUname() == null||userVo.getUname() == ""||
                userVo.getPassword() == null||userVo.getPassword() == ""||
                userVo.getEmail() == null||userVo.getEmail() == ""){
            model.addAttribute("uu",2);
            return "reg";
        }
        UserInfo selectUser=null;
        try {
             selectUser = userInfoService.selectUser(userVo.getUname());
        } catch (Exception e) { }
       if (selectUser == null) {
           Md5Hash md5Hash = new Md5Hash(userVo.getPassword(), null, 1024);
           UserInfo userInfo = new UserInfo();
           userInfo.setUserName(userVo.getUname());
           userInfo.setPassword(md5Hash.toString());
           userInfo.setStatus(userVo.getCustomer());
           userInfo.setEmail(userVo.getEmail());
           userInfo.setCreateTime(new Date());
           userInfoService.addUserInfo(userInfo);
           return "login";
       }
        model.addAttribute("uu",3);
        return "reg";
    }


}
