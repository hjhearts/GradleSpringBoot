package com.mygradle.commons.importSelect;

import org.springframework.beans.factory.annotation.Autowired;

public class UseMyBean {
    private MyBean myBean;

    @Autowired
    public void setMyBean(MyBean myBean) {
        this.myBean = myBean;
    }

    public void printMsg(){
        System.out.println(myBean.getMsg());
    }
}
