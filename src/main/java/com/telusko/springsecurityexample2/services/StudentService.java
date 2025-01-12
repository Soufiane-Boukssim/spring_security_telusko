package com.telusko.springsecurityexample2.services;

import com.telusko.springsecurityexample2.entities.Student;
import com.telusko.springsecurityexample2.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repo;

    public List<Student> getStudents() {
        return repo.findAll();
    }

    public Student addStudent(Student student) {
        return repo.save(student);
    }

}
