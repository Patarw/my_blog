package com.patarw.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private Integer views;
    private Boolean published;
    private Boolean recommended;
    private String day;
    private String month;
    private String year;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ManyToOne
    private Type type;
    @ManyToOne
    private User user;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tagList = new ArrayList<>();
    @OneToMany(mappedBy = "blog")
    private List<Comment> commentList = new ArrayList<>();

    public Blog(){

    }
}
