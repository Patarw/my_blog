package com.patarw.blog.web.admin;

import com.patarw.blog.entity.Type;
import com.patarw.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @PostMapping("/types")
    public String insertTypes(Type type,
                              RedirectAttributes attributes){
        Type t = typeService.insertType(type);
        if (t == null){
            attributes.addFlashAttribute("msg","添加失败!");
        }else{
            attributes.addFlashAttribute("msg","添加成功!");
        }
        return "redirect:/admin/types";
    }


    @GetMapping("/types/{id}/delete")
    public String deleteTypes(@PathVariable Long id,
                              RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("msg", "成功删除类目！");
        return "redirect:/admin/types";
    }

}
