package com.chengqj.study.springbootthymeleaf.controller;

import com.chengqj.study.springbootthymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @RequestMapping("/boot/index")
    public String index(Model model, HttpServletRequest request){
        model.addAttribute("msg","spring boot 集成 thymeleaf");

        Map<String,String> map = new HashMap<String,String>();
        map.put("one","asdf");
        map.put("two","wot");
        model.addAttribute("map",map);

        User user = new User();
        user.setId(100);
        user.setAddress("middle");
        user.setName("abiao");
        model.addAttribute("user",user);

        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user1 = new User();
            user1.setId(100 + i);
            user1.setAddress("middle");
            user1.setName("abiao");
            list.add(user1);
        }
        model.addAttribute("list",list);

        model.addAttribute("sex","nv");


        request.setAttribute("lisi","wangwu");
        request.getSession().setAttribute("lisi","wangwu");
        return "index";
    }

    @RequestMapping("/boot/user")
    @ResponseBody
    public String user(Model model,String id){

        return id;
    }
}
