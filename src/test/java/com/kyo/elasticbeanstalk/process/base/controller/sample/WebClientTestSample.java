package com.kyo.elasticbeanstalk.process.base.controller.sample;

import com.kyo.elasticbeanstalk.process.base.controller.HelloController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.net.ssl.SSLException;


// 랜덤포트 사용할때 서블릿 컨테이너가(내장 톰켓이) 뜬다.
// 이땐 MockMvc가 이 아니라 TestRestTemplate이나 WebTestClient를 써야 한다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebClientTestSample {
    // (스프링 5에서 새로 추가) 스프링 웹 플럭스 쪽에 추가된 레스트 클라이언트 중에 하나.
    // 기존 사용하던 레스트 클라이언트는 Synchronous(동기) - 요청 하나 보내고 기존에 하나 끝나야 다시 하나 보낼 수 있다.
    // asynchronous(비동기) 하다. 요청을 보내고 기다리는게 아니라, 응답이 오면 그때 바로 콜백이 온다.
    // 그때 이벤트가 오기 때문에 콜백을 바로 실행할 수 있다.
    // 테스트 코드에서도 웹 클라이언트랑 동일한 api를 사용 가능하다.
    WebTestClient client;

    @Autowired
    HelloController helloController;

    @BeforeEach
    void setUp(ApplicationContext context) throws SSLException {
        client = WebTestClient.bindToController(helloController).build();
        /*
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        client = WebTestClient.bindToServer(new ReactorClientHttpConnector(
                HttpClient.create().baseUrl("http://127.0.0.1:8443")
                        .secure(t -> t.sslContext(sslContext))
                ))
                .responseTimeout(Duration.ofSeconds(10))
                .build();
         */
    }


    @Test
    public void hello() throws Exception {

        client.get()
                .uri("/hello")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("hello kyo");


    }



}