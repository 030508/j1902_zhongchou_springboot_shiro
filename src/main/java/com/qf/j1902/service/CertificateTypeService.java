package com.qf.j1902.service;

import com.qf.j1902.pojo.CertificateType;

public interface CertificateTypeService {
    void  delType();
    void  addType(CertificateType certificateType);

    CertificateType findByTpe(String type);
}
