package com.qf.j1902.pojo;

import lombok.Data;

@Data
public class CertificateType {
    private Integer typeid;
    private  String type;
    private  String mingcheng;

    public CertificateType() {
    }

    public CertificateType(String type, String mingcheng) {
        this.type = type;
        this.mingcheng = mingcheng;
    }

    public CertificateType(Integer typeid, String type, String mingcheng) {
        this.typeid = typeid;
        this.type = type;
        this.mingcheng = mingcheng;
    }
}
