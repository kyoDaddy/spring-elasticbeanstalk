package com.kyo.elasticbeanstalk.process.base.controller.sample;

import com.kyo.elasticbeanstalk.config.prop.KyoProperties;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.time.Duration;


@JsonTest
class JsonTestSample {

    @Autowired
    JacksonTester<KyoProperties> json;


    @Test
    public void testSerialize() throws Exception {

        KyoProperties kyoProperties = new KyoProperties();
        kyoProperties.setAge(12);
        kyoProperties.setName("kyo");
        kyoProperties.setFullName("kyo Kim");
        kyoProperties.setSessionTimeout(Duration.ofSeconds(10));

        // Assert against a `.json` file in the same package as the test
        // Assertions.assertThat(this.json.write(kyoProp)).isEqualToJson("expected.json");

        // Or use JSON path based assertions
        Assertions.assertThat(this.json.write(kyoProperties))
                .hasJsonPathStringValue("name");
        Assertions.assertThat(this.json.write(kyoProperties))
                .extractingJsonPathStringValue("name")
                .isEqualTo("kyo");

    }


    @Test void testDeserialize() throws Exception {
        String content = "{\"age\":\"12\",\"name\":\"kyo\",\"fullName\":\"kyo Kim\"}";

        Assertions.assertThat(this.json.parseObject(content).getName())
                .isEqualTo("kyo");
    }

}