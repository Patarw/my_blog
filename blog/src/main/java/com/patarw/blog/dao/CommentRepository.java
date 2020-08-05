package com.patarw.blog.dao;

import com.patarw.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> getAllByBlogIdAndParentCommentId(Long id,Long pId);
    List<Comment> getAllByParentCommentId(Long pId);
}
