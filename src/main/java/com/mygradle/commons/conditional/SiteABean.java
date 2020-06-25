package com.mygradle.commons.conditional;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

//@Component
//@Conditional(SiteAConfigCondition.class)
public class SiteABean implements MsgBean {
    @Override
    public void printMsg() {
        System.out.println("Site A is working");
    }
}
