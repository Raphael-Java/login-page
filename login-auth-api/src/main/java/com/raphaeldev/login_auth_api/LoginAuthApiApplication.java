package com.raphaeldev.login_auth_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.raphaeldev.login_auth_api",
    "controllers", "infra", "domain", "dto", "repositories"
})
@EntityScan(basePackages = {"domain"})
@EnableJpaRepositories(basePackages = {"repositories"})
public class LoginAuthApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginAuthApiApplication.class, args);
    }
}