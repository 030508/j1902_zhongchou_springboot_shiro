package com.qf.j1902.service;

import com.qf.j1902.pojo.Label;

import java.util.List;

public interface LabelService {
    List<Label> labels(String cname);
}
