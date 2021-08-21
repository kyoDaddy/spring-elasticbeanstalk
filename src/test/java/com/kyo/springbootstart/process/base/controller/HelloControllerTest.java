package com.kyo.springbootstart.process.base.controller;

import com.kyo.springbootstart.process.base.service.HelloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired HelloController helloController;
    @Autowired HelloService helloService;

    @BeforeEach
    void setUp() {
        if(mockMvc == null) {
            mockMvc = MockMvcBuilders.standaloneSetup(helloController)
                    .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                    .alwaysDo(print())
                    .build();
        }
    }

    @Test
    public void hello2() throws Exception {

        // 요청 "hi"
        // 응답
        // - 모델 name : kyo
        // - 뷰 이름 : hi

        mockMvc.perform(get("/hello2")
                        .param("test", "test"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("hello2"))
                //.andExpect(forwardedUrl("/templates/hello2.html"))
                .andExpect(model().attribute("name", "kyo!!!"));

    }

}