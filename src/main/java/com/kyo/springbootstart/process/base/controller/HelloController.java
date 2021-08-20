package com.kyo.springbootstart.process.base.controller;

import com.kyo.springbootstart.process.base.service.HelloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        log.info("===============================");
        System.out.println("babo");
        return "hello " + helloService.getName();
    }

}
