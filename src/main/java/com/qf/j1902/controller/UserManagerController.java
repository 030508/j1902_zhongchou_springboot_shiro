package com.qf.j1902.controller;

import com.qf.j1902.pojo.RoleInfo;
import com.qf.j1902.pojo.UserInfo;
import com.qf.j1902.service.RoleInfoService;
import com.qf.j1902.service.UserInfoService;
import com.qf.j1902.vo.UserVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/manager")
@RequiresPermissions( value = {"用户管理"})
public class UserManagerController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleInfoService roleInfoService;


    @GetMapping("/user")
    public String userManager(Model model){
        String status = "管理";
        List<UserInfo> userInfos= userInfoService.findAllByStatus(status);
        model.addAttribute("users",userInfos);
        return  "user";
    }

    @DeleteMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(Integer id ){
        userInfoService.deleteUserById(id);
        roleInfoService.deleteUserRoleById(id);
        return  "1";
    }
    @GetMapping("/add")
    public String adduser(){
        return "add";
    }
    @PutMapping("/zengjiaUser")
    @ResponseBody
    public  String zengjiaUser(UserVo userVo, Model model){
        //邮箱格式正则表达式，用于邮箱格式验证
        String s = panduangeshi(userVo, model);
        if (s.equals("2")){
            return "2";
        }
        UserInfo selectUser=null;
        try {
            selectUser = userInfoService.selectUser(userVo.getUname());
        } catch (Exception e) { }
        if (selectUser == null) {
            UserInfo userInfo = newUserinfo(userVo);
            userInfoService.addUserInfo(userInfo);
            UserInfo user = userInfoService.selectUser(userInfo.getUserName());
            roleInfoService.addRoleById(user.getUserid());
            return "1";
        }
        model.addAttribute("uu",3);
        return "2";
    }

    @GetMapping("/edit")
    public String edit(String uname,Model model){
        UserInfo userInfo = userInfoService.selectUser(uname);
        model.addAttribute("user",userInfo);
        return "edit";
    }
    @GetMapping("/updateUser1")
    @ResponseBody
    public String updateUser1(UserVo userVo,Integer userid,Model model){
        String s = panduangeshi(userVo, model);
        System.out.println(userVo);
        if (s.equals("2")){
            return "2";
        }
        UserInfo userInfo = newUserinfo(userVo);
        System.out.println(userInfo);
        userInfoService.updateUserById(userInfo,userid);
        return "1";
    }

    public UserInfo newUserinfo(UserVo userVo){
        Md5Hash md5Hash = new Md5Hash(userVo.getPassword(), null, 1024);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userVo.getUname());
        userInfo.setPassword(md5Hash.toString());
        userInfo.setStatus(userVo.getCustomer());
        userInfo.setEmail(userVo.getEmail());
        userInfo.setCreateTime(new Date());
        return  userInfo;
    }
    public  String panduangeshi(UserVo userVo,Model model){
        String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (!userVo.getEmail().matches(regex)){
            return "2";
        }
        if(userVo.getUname() == null||userVo.getUname() == ""||
                userVo.getPassword() == null||userVo.getPassword() == ""||
                userVo.getEmail() == null||userVo.getEmail() == ""){
            return "2";
        }
        return "1";
    }


    @GetMapping("/assignRole")
    public  String assignRole(int id,Model model){
        Set<RoleInfo>  notRoles = roleInfoService.findNotRoleById(id);
        Set<RoleInfo> roles = roleInfoService.findUserRoleById(id);
        model.addAttribute("notRoles",notRoles);
        model.addAttribute("roles",roles);
        model.addAttribute("userid",id);
        return "assignRole";
    }
    @GetMapping("/updateRole")
    @ResponseBody
    public  String updateRole(Integer userid,String roles ){
       roleInfoService.deleteUserRoleById(userid);
        String[] split =  roles.split(",");
        for (String r:split){
            int i = Integer.parseInt(r);
            roleInfoService.addUserRoleByUserIdAndRoleId(userid,i);
       }
        return "1";
    }
    @GetMapping("/role")
    public  String role(Model model){
        List<RoleInfo> roleInfos = roleInfoService.findAll();
        model.addAttribute("roleInfos",roleInfos);
        return ("role");
    }
}
