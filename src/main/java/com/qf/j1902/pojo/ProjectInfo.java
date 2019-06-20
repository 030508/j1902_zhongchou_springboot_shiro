package com.qf.j1902.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectInfo {
    private Integer pifnfoid;  //id
    private Integer days;  //众筹天数
    private Integer isValid; // 是否有效
    private String entryname; //项目名称
    private String synopsis; //项目简介
    private String headimg;  //项目头图
    private String detailsimg; //项目详情
    private String selfintro; //自我介绍
    private String detailed;// 详细自我介绍
    private String call1; //联系电话
    private String centercall;//客服电话
    private String account; //收款账户
    private String idcard; //身份证号码
    private String zhuangtai; //项目状态
    private String uname; //用户名
    private double amount; //筹资金额
    private Date creatTime; //创建时间

}

