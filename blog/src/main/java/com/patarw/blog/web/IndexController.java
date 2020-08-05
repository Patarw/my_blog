package com.patarw.blog.web;

import com.patarw.blog.entity.Blog;
import com.patarw.blog.entity.Comment;
import com.patarw.blog.entity.Year;
import com.patarw.blog.service.BlogService;
import com.patarw.blog.service.CommentService;
import com.patarw.blog.service.TypeService;
import com.patarw.blog.service.YearService;
import com.patarw.blog.util.MarkdownUtils;
import com.patarw.blog.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private YearService yearService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/")
    public String index(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        List<Blog>  blogList = new ArrayList<>();
        Page<Blog> blogPage = blogService.listBlog(pageable);
        for (Blog blog : blogPage) {
            Blog b = new Blog();
            BeanUtils.copyProperties(blog,b);
            String content = b.getContent();
            b.setContent(MarkdownUtils.getText(MarkdownUtils.markdownToHtmlExtensions(content)));
            blogList.add(b);
        }
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("blog",blogList);
        return "index"; }
    @RequestMapping("/blog")
    public String blog(@RequestParam(value = "id") Long id,Model model){
        List<Comment> commentList = commentService.listCommentByIdAndPid(id,null);
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentVo commentVo = new CommentVo();
            BeanUtils.copyProperties(comment,commentVo);
            List<Comment> replyCommentList = commentService.listCommentByParentCommentId(comment.getId());
            if (replyCommentList.isEmpty()){
                commentVo.setFlag(false);
            }else {
                commentVo.setFlag(true);
                commentVo.setReplyCommentList(replyCommentList);
            }
            commentVoList.add(commentVo);
        }
        blogService.addView(blogService.getBlog(id));
        model.addAttribute("comment",commentVoList);
        model.addAttribute("blogs",blogService.getAndConvert(id));
        return "blog";
    }
    @RequestMapping("/types")
    public String types(Long id,Model model){
        if (id == null){
            id = new Long(6);
        }
        model.addAttribute("name",typeService.getType(id));
        model.addAttribute("blog",blogService.listBlogByTypeId(id));
        model.addAttribute("type",typeService.listType());
        return "types";
    }
    @RequestMapping("/archives")
    public String archives(String year,Model model){
        if (year == null){
            year = "2020";
        }
        model.addAttribute("time",year);
        model.addAttribute("year",yearService.listYear());
        model.addAttribute("list",blogService.listBlogByYear(year));
        return "archives";
    }
    @RequestMapping("/about")
    public String about(Model model){
        List<Comment> commentList = commentService.listCommentByIdAndPid(new Long(52),null);
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentVo commentVo = new CommentVo();
            BeanUtils.copyProperties(comment,commentVo);
            List<Comment> replyCommentList = commentService.listCommentByParentCommentId(comment.getId());
            if (replyCommentList.isEmpty()){
                commentVo.setFlag(false);
            }else {
                commentVo.setFlag(true);
                commentVo.setReplyCommentList(replyCommentList);
            }
            commentVoList.add(commentVo);
        }
        blogService.addView(blogService.getBlog(new Long(52)));
        model.addAttribute("comment", commentVoList);
        model.addAttribute("blogs",blogService.getAndConvert(new Long(52)));
        return "about";
    }
}
