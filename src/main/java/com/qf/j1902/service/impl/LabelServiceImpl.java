package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.LabelMapper;
import com.qf.j1902.pojo.Label;
import com.qf.j1902.service.LabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class LabelServiceImpl implements LabelService {
    @Resource
    private LabelMapper labelMapper;
    @Override
    public List<Label> labels(String cname) {
        return labelMapper.findAllByCateName(cname);
    }
}
