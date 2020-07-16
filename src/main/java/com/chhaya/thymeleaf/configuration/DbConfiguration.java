package com.chhaya.thymeleaf.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
public class DbConfiguration {

    @Bean
    public DataSource configure() {

        DataSourceBuilder builder = DataSourceBuilder.create();

        builder.driverClassName("org.postgresql.Driver");
        builder.url("jdbc:postgresql://localhost:5432/dgb-ams");
        builder.username("postgres");
        builder.password("qwer");

        return builder.build();

    }

}
