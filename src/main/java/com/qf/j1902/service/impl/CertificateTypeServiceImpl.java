package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.CertificateTypeMapper;
import com.qf.j1902.pojo.CertificateType;
import com.qf.j1902.service.CertificateTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CertificateTypeServiceImpl implements CertificateTypeService {
    @Resource
    private CertificateTypeMapper certificateTypeMapper;

    @Override
    public void delType() {
        certificateTypeMapper.deleteAll();
    }

    @Override
    public void addType(CertificateType certificateType) {
        certificateTypeMapper.insertCertificateType(certificateType);
    }

    @Override
    public CertificateType findByTpe(String type) {
        return certificateTypeMapper.findByTpe(type);
    }
}
