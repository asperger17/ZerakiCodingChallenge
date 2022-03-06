package com.gabrielowino.zerakicodingchallenge.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Entity
public class Course {

    @Id
    @Getter
    @Setter
    private String courseID;
    @Getter
    @Setter
    private String nameOfCourse;
    @Getter
    @Setter
    @ManyToOne
    private Institution institutionOfferingCourse;
    @Getter
    @Setter
    private Boolean active;
    @Getter
    @Setter
    private Date startDate;
    @Getter
    @Setter
    private Date endDate;
    @ManyToMany
    @Getter
    @Setter
    private List<Student> enrolledStudents;

    public Course() {
    }
}
