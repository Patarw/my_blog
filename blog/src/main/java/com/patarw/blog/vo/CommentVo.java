package com.patarw.blog.vo;

import com.patarw.blog.entity.Blog;
import com.patarw.blog.entity.Comment;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CommentVo {

    private Long id;
    private String nickname;
    private String content;
    private String avatar;
    private String email;
    private Date createTime;
    private Date updateTime;
    private boolean flag;

    private List<Comment> replyCommentList;
}
