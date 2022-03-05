package com.gabrielowino.zerakicodingchallenge.repositories;

import com.gabrielowino.zerakicodingchallenge.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    List<Course> findAllByOrderByNameOfCourseAsc();

    List<Course> findAllByOrderByNameOfCourseDesc();

    List<Course> findCoursesByInstitutionOfferingCourse_RegistrationNumber(String registrationNumber);

    List<Course> findCoursesByInstitutionOfferingCourse_RegistrationNumberAndNameOfCourse(
            String registrationNumber, String courseName);

}
