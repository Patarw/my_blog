package com.patarw.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "tagList")
    private List<Blog> blogList = new ArrayList<>();

    public Tag() {
    }
}
