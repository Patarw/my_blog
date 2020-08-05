package com.patarw.blog.web.admin;

import com.patarw.blog.entity.Blog;
import com.patarw.blog.entity.Type;
import com.patarw.blog.service.BlogService;
import com.patarw.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class PageController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/input")
    public String inputPage(Model model) {
        model.addAttribute("type",typeService.listType());
        return "admin/input";
    }
    @GetMapping("/index")
    public String indexPage(){
        return "admin/index";
    }
    @GetMapping("/list")
    public String listPage(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                           Model model, Blog blog){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/list";
    }
    @GetMapping("/types")
    public String typelistPage(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                           Pageable pageable, Model model){
        model.addAttribute("page",typeService.listTypeByPage(pageable));
        return "admin/typelist";
    }
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    @RequestMapping("/edit")
    public String editPage(@RequestParam(value = "id") Long id,Model model){
        model.addAttribute("blog",blogService.getBlog(id));
        model.addAttribute("type",typeService.listType());
        return "admin/edit";
    }


}
