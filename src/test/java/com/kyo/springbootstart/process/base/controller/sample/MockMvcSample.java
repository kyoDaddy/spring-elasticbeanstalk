package com.kyo.springbootstart.process.base.controller.sample;

import com.kyo.springbootstart.process.base.controller.HelloController;
import com.kyo.springbootstart.process.base.service.HelloService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class}) // junit 5 기준 (OutputCaptureExtension )
@WebMvcTest(HelloController.class)
class MockMvcSample {

    @Autowired
    MockMvc mockMvc;

    // 목빈으로 설정하면 sampleService 를 mockSampleService 만들어서 쓰는 것
    @MockBean
    HelloService mockSerivce;

    @Test
    void hello(CapturedOutput capturedOutput) throws Exception {

        when(mockSerivce.getName()).thenReturn("kyo"); // getNaem 이 호출되면 kyo 리턴받는다

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello kyo"))
                .andDo(print());

        Assertions.assertThat(capturedOutput.toString())
                .contains("===============================")
                .contains("babo");


    }

}