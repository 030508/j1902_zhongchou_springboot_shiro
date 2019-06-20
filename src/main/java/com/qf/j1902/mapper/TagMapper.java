package com.qf.j1902.mapper;

import com.qf.j1902.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    List<Tag> findTagsByLabelId(Integer labelid);
}
