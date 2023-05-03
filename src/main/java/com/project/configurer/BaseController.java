package com.project.configurer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ThreadPoolExecutor;

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