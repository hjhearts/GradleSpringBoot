package com.mygradle.commons.cache;

public class Car {
    private String name;
    private String color;

    public Car(String name){
        this.name = name;
    }

    public Car(String name, String color){
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString(){
        return "Car{" +
                "name='" + name + "'/" +
                "color='" + color + "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
