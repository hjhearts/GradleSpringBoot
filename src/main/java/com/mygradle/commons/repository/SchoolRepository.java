package com.mygradle.commons.repository;

import com.mygradle.commons.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
