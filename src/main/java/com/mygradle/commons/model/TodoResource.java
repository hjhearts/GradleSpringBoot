package com.mygradle.commons.model;

import org.springframework.hateoas.RepresentationModel;

public class TodoResource extends RepresentationModel {
    private String title;

    public TodoResource(){}

    public TodoResource(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

}
