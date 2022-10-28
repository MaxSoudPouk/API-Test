package com.example.etlkycsimregisterapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication(scanBasePackages = {
        " com.example.etlkycsimregisterapi"
})
public class EtlkycsimregisterapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtlkycsimregisterapiApplication.class, args);
    }
    @Bean
    public CommandLineRunner CommandLineRunnerBean(){
        return  (args) ->{
            System.out.println("In Startup main");
//			threadRead threadRead = new threadRead();
//			threadRead.contextInitialized(null);
        };
    }
    //System.out.print("9999999998888888999999999999999");
}
