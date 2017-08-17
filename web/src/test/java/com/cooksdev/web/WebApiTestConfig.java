package com.cooksdev.web;

import com.cooksdev.web.security.OAuth2AuthorizationConfig;
import com.cooksdev.web.security.OAuth2ResourceConfig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = ASSIGNABLE_TYPE,classes = {WebApplication.class})})
@ComponentScan({"com.cooksdev.data", "com.cooksdev.service", "com.cooksdev.web"})
@Transactional(propagation = Propagation.NEVER)
@Import({OAuth2AuthorizationConfig.class, OAuth2ResourceConfig.class})
public class WebApiTestConfig {
}
