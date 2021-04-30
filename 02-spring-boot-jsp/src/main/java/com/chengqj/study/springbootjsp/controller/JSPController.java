package com.chengqj.study.springbootjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JSPController {

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("msg","spring boot 继承 jsp");
        return "index";
    }

}
