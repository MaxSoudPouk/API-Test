package com.etl.kyc.etlkycdispayimage;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfigurer implements WebMvcConfigurer {
    public static String uploadDirectory= System.getProperty("user.home") + "\\images";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + uploadDirectory+"\\");
    }
}
