package com.gabrielowino.zerakicodingchallenge.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Institution {

    @Id
    @Getter
    private String registrationNumber;
    @Getter
    @Setter
    private String nameOfInstitution;
    @OneToMany(mappedBy = "institutionOfferingCourse")
    private Set<Course> coursesOffered;

    public Institution() {
    }
}
