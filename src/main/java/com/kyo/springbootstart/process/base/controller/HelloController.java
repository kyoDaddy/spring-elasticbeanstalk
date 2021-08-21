package com.kyo.springbootstart.process.base.controller;

import com.kyo.springbootstart.process.base.service.HelloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class HelloController {

    @GetMapping("hello2")
    public String hello2(Model model) {
        model.addAttribute("name", "kyo!!!");
        return "hello2";
    }

}
