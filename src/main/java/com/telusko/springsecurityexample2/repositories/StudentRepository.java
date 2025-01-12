package com.telusko.springsecurityexample2.repositories;

import com.telusko.springsecurityexample2.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
