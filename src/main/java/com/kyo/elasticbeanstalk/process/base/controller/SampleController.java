package com.kyo.elasticbeanstalk.process.base.controller;

import com.kyo.elasticbeanstalk.config.exception.ApiException;
import com.kyo.elasticbeanstalk.config.exception.ExceptionEnum;
import com.kyo.elasticbeanstalk.process.base.dto.Person;
import com.kyo.elasticbeanstalk.process.base.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * HttpMessageConverters
 *    - HTTP 요청 본문을 객체로 변경하거나, 객체를 HTTP 응답 본문으로 변경할 때 사용
 */
// Rest 붙이면 이 클래스의 모든 메서드에 @ResponseBody 붙인것과 동일
@RestController
@Slf4j
public class SampleController {

    private final HelloService helloService;

    public SampleController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping(value = "/hello",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE,
                        MediaType.TEXT_XML_VALUE }
    )
    public String hello() {
        log.info("===============================");
        System.out.println("babo");
        throw new ApiException(ExceptionEnum.SECURITY_01);
        //return "hello " + helloService.getName();
    }

    @GetMapping("/message")
    // @RequestBody 요청시 본문에 들어있는 메세지를 HTTP 메세지 컨버터를 사용해서 컨버전을 한다.
    public String message(@RequestBody String body) {
        System.out.println("message1");
        return body;
    }

    @GetMapping("/jsonMessage")
    // 부트는 기본적으로 jackson2가 제공하는 objectMapper 사용해서 컨버전
    public Person jsonMessage(@RequestBody Person person) {
        return person;
    }


}
