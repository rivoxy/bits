package com.bits.kata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The entry point of the Spring Boot application.
 *
 * @author Rivo
 */
@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.bits")
@EnableJpaRepositories(basePackages = "com.bits")
@EnableTransactionManagement
public class KataTennisSpringBootApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(KataTennisApplicationResourceConfig.class);
        return builder.sources(KataTennisSpringBootApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(KataTennisSpringBootApplication.class, args);
    }

}
