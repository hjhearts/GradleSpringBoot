package com.mygradle.commons.conditional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MainConfig {
    private MsgBean msgBean;

    @Autowired
    public void setMsgBean(MsgBean msgBean) {
        this.msgBean = msgBean;
    }
}
