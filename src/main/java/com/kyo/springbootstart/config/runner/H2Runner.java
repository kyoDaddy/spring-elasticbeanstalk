package com.kyo.springbootstart.config.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

/**
 * h2 inmemory db
 *  - http://localhost:8443/h2-console
 */
@Component
@Slf4j
public class H2Runner implements ApplicationRunner {

    final DataSource source;
    final JdbcTemplate jdbcTemplate;

    public H2Runner(DataSource source, JdbcTemplate jdbcTemplate) {
        this.source = source;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // java 8 리소스 관련 (try 끝나면 리소스 자동 정리)
        try(Connection connection = source.getConnection()) {
            log.info("h2 inmemory db url --> {} ", connection.getMetaData().getURL());
            log.info("h2 inmemory db username --> {} ", connection.getMetaData().getUserName());

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE USER (ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        }

        jdbcTemplate.execute("INSERT INTO USER VALUES (1, 'kyo')");


    }
}
