package com.mygradle.commons.service;

import com.mygradle.commons.model.School;
import com.mygradle.commons.model.Student;
import com.mygradle.commons.repository.SchoolRepository;
import com.mygradle.commons.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SchoolService {
    private SchoolRepository schoolRepository;

    @Autowired
    public void setSchoolRepository(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void findStudentInfo(){
        School school1 = new School("SinJang");
        school1.registerStudent(new Student("JuSeong"));
        school1.registerStudent(new Student("MinHyeok"));

        School school2 = new School("JeongBo");
        school2.registerStudent(new Student("SeonMin"));
        school2.registerStudent(new Student("JunYeong"));

        schoolRepository.save(school1);
        schoolRepository.save(school2);
        studentRepository.save(new Student("Han"));
        List<School> schools = schoolRepository.findAll();

        for(School s : schools){
            System.out.println(s);
        }
    }
}
