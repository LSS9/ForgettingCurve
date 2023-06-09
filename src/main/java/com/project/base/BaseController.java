package com.project.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class BaseController {


    @RequestMapping("/test")
    public String test() throws IOException {


//        File file = ResourceUtils.getFile("classpath:templates/4.html");
//        String content = new String(Files.readAllBytes(file.toPath()));
//        model.addAttribute("content", content);
        return "index";
    }

    @RequestMapping("/test2")
    public String test2() throws IOException {
//        File file = ResourceUtils.getFile("classpath:templates/4.html");
//        String content = new String(Files.readAllBytes(file.toPath()));
//        model.addAttribute("content", content);
        return "table";
    }

}