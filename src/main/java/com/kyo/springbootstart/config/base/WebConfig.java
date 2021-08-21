package com.kyo.springbootstart.config.base;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 기존에 제공하는 리소스 핸들러는 유지하면서, 내가 원하는 리소스 핸들러만 추가 가능
    // 리소스 핸들러 추가 가능
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/m/**") // m으로 시작하는 요청이 오면
                .addResourceLocations("classpath:/m/") // 클래스 패스 기준으로 m디렉토리 밑에서 제공한다. (반드시 / 로 끝내야함)
                .setCachePeriod(20); // 캐싱전략(초단위)

    }

}
