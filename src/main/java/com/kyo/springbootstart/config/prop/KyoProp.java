package com.kyo.springbootstart.config.prop;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @ConfigurationProperties
 *      - 자동 설정 클래스 내의 스프링 빈을 property 파일을 통해 재정의 하고자 할 때는 이 어노테이션을 사용한다.
 *      - build.gradle 내 spring-boot-configuration-processor dependency 추가 필요
 *      - Configuration bean 등록 후 사용
 *      - change active spring profile 설정 필요
 */
@Slf4j
@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "kyo")
public class KyoProp {

    private String name;
    private int age;
    private String fullName;

    // 프로퍼티 타입 컨버전
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration sessionTimeout = Duration.ofSeconds(30);

    @PostConstruct
    private void showProperties() {

        log.info("name --> {}, {}, {}", name, fullName, age);
        log.info("sessionTimeout --> {}", sessionTimeout);

    }

}


