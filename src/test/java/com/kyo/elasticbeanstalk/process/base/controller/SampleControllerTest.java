package com.kyo.elasticbeanstalk.process.base.controller;

import com.google.gson.Gson;
import com.kyo.elasticbeanstalk.process.base.dto.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SampleController sampleController;

    @BeforeEach
    void setUp() {
        if(mockMvc == null) {
            mockMvc = MockMvcBuilders.standaloneSetup(sampleController)
                    .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                    .alwaysDo(print())
                    .build();
        }
    }

    @Test
    void jsonMessage() throws Exception {

        Person person = Person.builder()
                .id(1L)
                .name("kyo")
                .build();

        String jsonString = new Gson().toJson(person);

        this.mockMvc.perform(get("/message")
                        .contentType(MediaType.APPLICATION_JSON) // json으로 컨텐츠를 보낼거고
                        .accept(MediaType.APPLICATION_JSON) // json으로 응답을 기대한다.
                        .content(jsonString))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(1))
                        .andExpect(jsonPath("$.name").value("kyo"));

    }



}