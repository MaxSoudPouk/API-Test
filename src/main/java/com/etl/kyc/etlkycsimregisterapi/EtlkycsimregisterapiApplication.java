package com.etl.kyc.etlkycsimregisterapi;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.awt.image.BufferedImage;


@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class EtlkycsimregisterapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtlkycsimregisterapiApplication.class, args);

    }


    @Bean
    public HttpMessageConverter<BufferedImage> bufferedImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }


    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            //	System.out.println("In Startup main");
//			threadRead threadRead = new threadRead();
//			threadRead.contextInitialized(null);
        };
    }


    //System.out.print("9999999998888888999999999999999");

}




