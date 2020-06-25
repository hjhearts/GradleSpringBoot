package com.mygradle.commons.service;

import com.mygradle.commons.config.MyAnnotation;

public class MyService {
    @MyAnnotation(strValue = "hi", intValue = 391)
    public void printSomething(){
        System.out.println("test my annotation");
    }
}
