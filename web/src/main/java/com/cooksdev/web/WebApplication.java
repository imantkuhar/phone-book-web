package com.cooksdev.web;

import com.cooksdev.web.security.OAuth2AuthorizationConfig;
import com.cooksdev.web.security.OAuth2ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.cooksdev.data")
@ComponentScan({"com.cooksdev.data", "com.cooksdev.service", "com.cooksdev.web"})
@SpringBootApplication(exclude = {RepositoryRestMvcAutoConfiguration.class, HypermediaAutoConfiguration.class})
@EntityScan(basePackages = "com.cooksdev.data")
@Import({
        OAuth2AuthorizationConfig.class,
        OAuth2ResourceConfig.class,
})
public class WebApplication{

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}

