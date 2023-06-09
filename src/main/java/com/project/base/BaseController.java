package com.project.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class BaseController {


    @RequestMapping("/test")
    public String test(){


//        File file = ResourceUtils2222222222222222222222.getFile("classpath:templates/4.html");
//        String content = new Stdfgdfgdfgring(Files.readAllBytes(file.toPath()));
//        model.addAttribute("content", content);
        return "index";
    }

    @RequestMapping("/test2")
    public String test2(){
//        File file = ResourceUtils.getFile("classpath:templates/4.html");
//        String content = new String(Files.readAllBytes(file.toPath()));
//        model.addAttribute("content", content);
        return "table";
    }

}