package com.qf.j1902.mapper;

import com.qf.j1902.pojo.Label;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LabelMapper {
    List<Label> findAllByCateName(String name);
}
