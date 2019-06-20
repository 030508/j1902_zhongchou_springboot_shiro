package com.qf.j1902.controller;

import com.qf.j1902.pojo.RealName;
import com.qf.j1902.pojo.UserInfo;
import com.qf.j1902.service.RealNameService;
import com.qf.j1902.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manager")
@RequiresPermissions( value = {"业务审核"})
public class RealNameAuditsController {
    @Autowired
    private RealNameService realNameService;
    @Autowired
    private UserInfoService userInfoService;
    @GetMapping("auth_cert")
    public String authCert(Model model){
        String ss = "审核中";
        List<RealName> realNames= realNameService.selectAllByCode(ss);
        model.addAttribute("realNames",realNames);
        return "auth_cert";
    }

    @GetMapping("real_name")
    public String real_name(String id,Model model){
        RealName realName= realNameService.selectRealNameByUname(id);
        String img = realName.getImg();
        String[] imgs = img.split(",");
        model.addAttribute("realName",realName);
        model.addAttribute("imgs",imgs);
        return "realName_verify";
    }

    @RequestMapping(value = "shenhe",method = RequestMethod.GET)
    @ResponseBody
    public String shenhe(String action,String id ,String yijian ){
        if ("adopt".equals(action)){
            UserInfo userInfo = userInfoService.selectUser(id);
            realNameService.aaddUserRole(userInfo.getUserid(),6);
        }
        String code = "adopt".equals(action) ? "已认证":"认证失败";
        boolean b  = realNameService.updateCodeByUserName( code,yijian,id);

        return  b? "1":"2";
    }
}

