package com.qf.j1902.vo;

import lombok.Data;


import javax.validation.constraints.NotNull;

@Data
public class UserVo {
    @NotNull(message="用户名不能为空")
    private  String uname ;
    @NotNull(message="密码名不能为空")
    private  String password ;
    private  String customer;
    private String email;

    public UserVo() {
    }

    public UserVo(String uname, String password, String customer) {
        this.uname = uname;
        this.password = password;
        this.customer = customer;
    }

    public UserVo(String uname, String password, String customer, String email) {
        this.uname = uname;
        this.password = password;
        this.customer = customer;
        this.email = email;
    }
}
