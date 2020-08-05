package com.patarw.blog.web.admin;

import com.patarw.blog.entity.User;
import com.patarw.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.login(username,password);
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "redirect:/admin/index";
        }else{
            attributes.addFlashAttribute("message","用户名和密码错误");
            return "redirect:/admin";  //重新定向到/admin
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
