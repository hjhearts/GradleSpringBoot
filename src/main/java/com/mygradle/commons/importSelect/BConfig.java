package com.mygradle.commons.importSelect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class BConfig {
    @Bean
    public MyBean myBean(){
        return new MyBean("from bConfig");
    }
}
