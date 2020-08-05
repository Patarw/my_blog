package com.patarw.blog.service.impl;

import com.patarw.blog.dao.CommentRepository;
import com.patarw.blog.entity.Blog;
import com.patarw.blog.entity.Comment;
import com.patarw.blog.exception.NotFoundException;
import com.patarw.blog.service.BlogService;
import com.patarw.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BlogService blogService;

    @Override
    public Comment insert(Comment comment) {
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> listCommentByIdAndPid(Long id, Long pId) {
        return commentRepository.getAllByBlogIdAndParentCommentId(id,pId);
    }

    @Override
    public List<Comment> listCommentByParentCommentId(Long pId) {
        return commentRepository.getAllByParentCommentId(pId);
    }
}
