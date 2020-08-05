package com.patarw.blog.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String content;
    private String avatar;
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    @ManyToOne
    private Blog blog;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();
    @ManyToOne
    private Comment parentComment;

    public Comment() {
    }
}
