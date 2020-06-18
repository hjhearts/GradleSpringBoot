package com.mygradle.commons.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class School {
    @Id
    @Column(name = "SCHOOL_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String telNumber;
    @OneToMany(mappedBy = "school")
    private Set<Student> students;
    public School(){

    }

    public School(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void registerStudent(Student s){
        if(students == null){
            students = new HashSet<>();
        }
        students.add(s);
    }

    @Override
    public String toString(){
        StringBuilder studentInfos = new StringBuilder();
        for (Student s: students) {
            assert false;
            studentInfos.append(s.getUserName()).append(", ");
        }
        assert false;
        return "{id='" + id + "', name='" + name + "', address='" + address + "', telNumber='" + telNumber + "'\n"
                + "studentSet=" + studentInfos.toString() + "}";
    }
}
