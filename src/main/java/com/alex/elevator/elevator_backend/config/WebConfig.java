package com.alex.elevator.elevator_backend.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/webjars/", "/js/", "/css/")
                .addResourceLocations("classpath:/META-INF/resources/webjars/", "classpath:/static/js/", "classpath:/static/css/")
                .setCachePeriod(3600)
                .resourceChain(true);


    }
}