package com.ygz.blog.web;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.ygz.blog.NotFoundException;
import com.ygz.blog.dao.UserRepository;
import com.ygz.blog.po.User;
import com.ygz.blog.service.BlogService;
import com.ygz.blog.service.TagService;
import com.ygz.blog.service.TypeService;
import com.ygz.blog.service.UserService;
import com.ygz.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Id;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String index(@PageableDefault(size=8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog, Model model){
        userService.updateViews();
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("type",typeService.listTypeTop(6));
        model.addAttribute("tag",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommend8logTop(8));
        return "index";
    }
    @PostMapping("/search")
    public String search(@PageableDefault(size=8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog("%"+query+"%", pageable));
        model.addAttribute("query",query);
        return "search";
    }
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.listRecommend8logTop(3));
        return "_fragments :: newblogList";
    }
    //关于我页面
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
