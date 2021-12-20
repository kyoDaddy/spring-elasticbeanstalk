package com.kyo.elasticbeanstalk.process.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HelloController {

    @GetMapping("hello2")
    public String hello2(Model model) {
        model.addAttribute("name", "kyo!!!");
        return "hello2";
    }

}
