package com.mygradle.commons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CommonsApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(CommonsApplication.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        for(String beanName : beanNames){
            System.out.println(beanName);
        }
    }

}
