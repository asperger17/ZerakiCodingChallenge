package com.gabrielowino.zerakicodingchallenge.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Student {
    @Id
    @Getter
    @Setter
    private String uniqueStudentID;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String middleName;
    @Getter
    @Setter
    private String lastName;
    @ManyToOne
    @Getter
    @Setter
    private Institution enrolledInstitution;
    @ManyToMany(mappedBy = "enrolledStudents")
    @Getter
    @Setter
    private List<Course> enrolledCourses;

    public Student() {
    }
}
