package com.qf.j1902.controller;

import com.qf.j1902.pojo.RealName;
import com.qf.j1902.service.RealNameService;
import com.qf.j1902.utils.Sessionkey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manager")
public class MemberController {
    @Autowired
    private RealNameService realNameService;
    @GetMapping("/member")
    public  String maber(HttpSession session, Model model){
        String  uname = (String)session.getAttribute("uname");
        RealName realName =null;
        try {
            realName = realNameService.selectRealNameByUname(uname);
        } catch (NullPointerException e){
            session.setAttribute(Sessionkey.REAL_CODE, realName.getCode());
            session.setAttribute(Sessionkey.REAL_TYPE, realName.getType());
        }
        if (realName !=null) {
            session.setAttribute(Sessionkey.REAL_CODE, realName.getCode());
            session.setAttribute(Sessionkey.REAL_TYPE, realName.getType());
        }
        return "member";
    }
    @GetMapping("/minecrowdfunding")
    public  String minecrowdfunding(){

        return "minecrowdfunding";
    }
}
