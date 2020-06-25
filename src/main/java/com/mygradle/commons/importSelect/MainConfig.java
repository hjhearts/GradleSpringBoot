package com.mygradle.commons.importSelect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableAutoMyModule(value = "someValue")
public class MainConfig {
    @Bean
    public UseMyBean useMyBean(){
        return new UseMyBean();
    }
}
