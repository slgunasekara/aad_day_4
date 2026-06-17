package com.ijse.aad_75.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long schoolId;
    private String schoolName;
    private String schoolLocation;

    @OneToMany(mappedBy = "school",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Student> studentList;
}
