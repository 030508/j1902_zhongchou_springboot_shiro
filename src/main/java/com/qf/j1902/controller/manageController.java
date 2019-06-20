package com.qf.j1902.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
@RequiresPermissions(value = {"页面显示"})
public class manageController {
    @GetMapping("/main")
    public  String main(){
        return "main";
    }
}
