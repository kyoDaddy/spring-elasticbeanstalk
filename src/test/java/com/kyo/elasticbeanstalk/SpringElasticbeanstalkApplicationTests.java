package com.kyo.elasticbeanstalk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
class SpringElasticbeanstalkApplicationTests {

    @Autowired
    Environment environment;

    @Test
    void contextLoads() {

        Assertions.assertEquals(environment.getProperty("daemon.nick-name"), "Developer test");
        Assertions.assertEquals(environment.getProperty("kyo.full-name"), "kyo Kim");

    }



}
