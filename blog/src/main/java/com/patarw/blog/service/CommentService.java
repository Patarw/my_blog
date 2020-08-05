package com.patarw.blog.service;


import com.patarw.blog.entity.Comment;
import java.util.List;


public interface CommentService {
     Comment insert(Comment comment);

     List<Comment> listCommentByIdAndPid(Long id,Long pId);

     List<Comment> listCommentByParentCommentId(Long pId);
}
