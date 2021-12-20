package com.kyo.elasticbeanstalk.process.base.controller.sample;

import com.kyo.elasticbeanstalk.process.base.service.HelloService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestRestTemplateSample {
    @Autowired
    TestRestTemplate testRestTemplate;

    // 근데 이 컨트롤러가 서비스까지 연결되어 있다.(테스트 커짐)
    @MockBean
    HelloService mockHelloService;

    @Test
    public void hello() throws Exception {

        when(mockHelloService.getName()).thenReturn("kyo2");
        // HelloService 얘를 모킹해서 @MockBean 을 사용해 그 빈을 교체한 것.

        // 내장 톰캣을 호출
        String result = testRestTemplate
                .getForObject("/hello", String.class);
        Assertions.assertThat(result).isEqualTo("hello kyo2");

    }



}