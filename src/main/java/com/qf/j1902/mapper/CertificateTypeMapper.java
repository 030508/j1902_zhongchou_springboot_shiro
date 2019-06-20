package com.qf.j1902.mapper;

import com.qf.j1902.pojo.CertificateType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CertificateTypeMapper {
    void  deleteAll();
    void insertCertificateType(CertificateType certificateType);

    CertificateType findByTpe(String type);
}
