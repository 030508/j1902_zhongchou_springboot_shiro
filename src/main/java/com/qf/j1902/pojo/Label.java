package com.qf.j1902.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Label {
    private Integer labelid;
    private String labelName;
    private String explain;
    private List<Tag> tags;
}
