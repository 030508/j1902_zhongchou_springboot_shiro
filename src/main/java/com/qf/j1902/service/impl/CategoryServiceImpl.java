package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.CategoryMapper;
import com.qf.j1902.pojo.Category;
import com.qf.j1902.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> categorys() {
        return categoryMapper.findAll();
    }
}
