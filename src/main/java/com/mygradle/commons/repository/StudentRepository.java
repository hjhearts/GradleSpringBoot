package com.mygradle.commons.repository;

import com.mygradle.commons.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
