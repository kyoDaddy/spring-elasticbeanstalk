package com.kyo.elasticbeanstalk.config.runner;


import com.kyo.elasticbeanstalk.config.prop.DaemonProp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SampleRunner implements ApplicationRunner {
    @Value("${server.port}")
    private String port;

    private final DaemonProp daemonProp;

    public SampleRunner(DaemonProp daemonProp) {
        this.daemonProp = daemonProp;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //
        Arrays.stream(args.getSourceArgs())
                .forEach(System.out::println);

        System.out.println("on port (runner) : " + port);
        System.out.println("on port (runner) : " + daemonProp.getNickName());
    }
}
