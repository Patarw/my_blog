package com.patarw.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogList = new ArrayList<>();
    public Type() {
    }
}
