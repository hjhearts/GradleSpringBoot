package com.mygradle.commons.conditional;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

//@Component
//@Conditional(SiteBConfigCondition.class)
public class SiteBBean implements MsgBean {
    @Override
    public void printMsg() {
        System.out.println("Site B is working");
    }
}
