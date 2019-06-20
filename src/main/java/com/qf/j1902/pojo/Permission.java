package com.qf.j1902.pojo;

import lombok.Data;

@Data
public class Permission {
    private  Integer perId;
    private String perName;
    private String menuName;
    private String menuType;
    private String menuUrl;
    private String menuCode;
    private String parentCode;
    private String perDesc;
    private  Integer ifVilid;


}
