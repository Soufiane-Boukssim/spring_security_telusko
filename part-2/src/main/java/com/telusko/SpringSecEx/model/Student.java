package com.telusko.SpringSecEx.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Student {
    @Id
    private int id;
    private String name;
    private int marks;
}