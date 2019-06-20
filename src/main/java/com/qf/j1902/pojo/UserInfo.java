package com.qf.j1902.pojo;

import lombok.Data;

import java.util.Date;

/*
*
* 用户信息
* */
@Data
public class UserInfo {
    private Integer userid;  //用户id
    private  String userName;//用户名
    private  String password;//用户密码
    private  String status;//用户身份
    private Date createTime;//创建时间
    private String email;//邮箱

}

