package com.patarw.blog.web;

import com.patarw.blog.entity.Comment;
import com.patarw.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/about/insert")
    public String insertComment(Comment comment){
        commentService.insert(comment);
        return "redirect:/about";
    }

    @PostMapping("/blog/insert")
    public String insertCommentBlog(Comment comment){
        commentService.insert(comment);
        return "redirect:/blog?id="+comment.getBlog().getId();
    }
}
