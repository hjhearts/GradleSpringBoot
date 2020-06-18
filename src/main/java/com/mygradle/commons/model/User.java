package com.mygradle.commons.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel
public class User {
    private int userNo;
    private String userId;
    private String email;
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date regDate;

    public User(){}

    public User(int userNo, String userId, String email, String userName, Date regDate) {
        this.userNo = userNo;
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.regDate = regDate;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
