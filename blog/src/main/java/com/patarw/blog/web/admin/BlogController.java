package com.patarw.blog.web.admin;

import com.patarw.blog.entity.Blog;
import com.patarw.blog.entity.User;
import com.patarw.blog.service.BlogService;
import com.patarw.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/blog/{id}/delete")
    public String deleteTypes(@PathVariable Long id,
                              RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("msg", "成功删除博客！");
        return "redirect:/admin/list";
    }
    @GetMapping("/blog/{id}/input")
    public String editBlog(@PathVariable Long id, Model model){
        return "admin/edit";
    }

    @PostMapping("/blog/save")
    public String insertBlog(Blog blog, HttpSession session,
                           RedirectAttributes attributes){
        blog.setUser((User) session.getAttribute("user"));
        Blog b = blogService.saveBlog(blog);
        if (b == null){
            attributes.addFlashAttribute("msg","添加失败!");
        }else{
            attributes.addFlashAttribute("msg","添加成功!");
        }
        return "redirect:/admin/list";
    }

    @RequestMapping("/blog/update")
    public String updatePage(@RequestParam(value = "id") Long id, Blog blog,HttpSession session,
                             RedirectAttributes attributes){
        blog.setUser((User) session.getAttribute("user"));
        Blog b = blogService.updateBlog(id,blog);
        if (b == null){
            attributes.addFlashAttribute("msg","更新失败!");
        }else{
            attributes.addFlashAttribute("msg","更新成功!");
        }
        return "redirect:/admin/list";
    }

}
