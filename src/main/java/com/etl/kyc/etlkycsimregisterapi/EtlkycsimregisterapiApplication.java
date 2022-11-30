package com.etl.kyc.etlkycsimregisterapi;

import com.etl.kyc.etlkycsimregisterapi.uploadfile.property.FileStorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.awt.image.BufferedImage;

@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan
@AutoConfigureAfter(DispatcherServletAutoConfiguration.class)

@EnableConfigurationProperties({
        FileStorageProperties.class
})
@SpringBootApplication(scanBasePackages = {
        " com.example.speingbootsoapclient"
})
public class EtlkycsimregisterapiApplication implements WebMvcConfigurer {


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
            System.out.println("In Startup main");
//			threadRead threadRead = new threadRead();
//			threadRead.contextInitialized(null);
        };
    }


    //System.out.print("9999999998888888999999999999999");
}
