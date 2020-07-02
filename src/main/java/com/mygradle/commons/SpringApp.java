package com.mygradle.commons;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class);
    }

    @Bean
    public CommandLineRunner runner(){
        return (args) -> System.out.println("runnable jar running");
    }
}
