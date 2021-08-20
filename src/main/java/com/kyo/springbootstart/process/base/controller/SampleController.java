package com.kyo.springbootstart.process.base.controller;

import com.kyo.springbootstart.process.base.dto.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Rest 붙이면 이 클래스의 모든 메서드에 @ResponseBody 붙인것과 동일
@RestController
public class SampleController {

    @GetMapping("/message")
    // @RequestBody 요청시 본문에 들어있는 메세지를 HTTP 메세지 컨버터를 사용해서 컨버전을 한다.
    public String message(@RequestBody String body) {
        System.out.println("message");
        return body;
    }

    @GetMapping("/jsonMessage")
    // 부트는 기본적으로 jackson2가 제공하는 objectMapper 사용해서 컨버전
    public Person jsonMessage(@RequestBody Person person){
        return person;
    }

}
