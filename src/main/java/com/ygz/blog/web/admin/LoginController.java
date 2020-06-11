package com.ygz.blog.web.admin;

import com.ygz.blog.dao.UserRepository;
import com.ygz.blog.po.User;
import com.ygz.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
   @Autowired
   private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    @PostMapping("/login")      //RedirectAttributes 是Spring mvc 3.1版本之后出来的一个功能，专门用于重定向之后还能带参数跳转的的工具类
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes){
        //@RequestParam表示参数为必传
        User user=userService.checkUser(username,password);
        if(user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else{
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";

    }
}
