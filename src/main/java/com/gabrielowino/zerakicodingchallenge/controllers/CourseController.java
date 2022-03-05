package com.gabrielowino.zerakicodingchallenge.controllers;

import com.gabrielowino.zerakicodingchallenge.exceptionhandlers.exceptions.BadRequestException;
import com.gabrielowino.zerakicodingchallenge.exceptionhandlers.exceptions.ResourceIDExistsException;
import com.gabrielowino.zerakicodingchallenge.exceptionhandlers.exceptions.ResourceNotFoundException;
import com.gabrielowino.zerakicodingchallenge.models.Course;
import com.gabrielowino.zerakicodingchallenge.models.Institution;
import com.gabrielowino.zerakicodingchallenge.repositories.CourseRepository;
import com.gabrielowino.zerakicodingchallenge.repositories.InstitutionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class CourseController {

    private final CourseRepository courseRepository;
    private final InstitutionRepository institutionRepository;

    public CourseController(CourseRepository courseRepository, InstitutionRepository institutionRepository) {
        this.courseRepository = courseRepository;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/courses")
    List<Course> listAll(@RequestParam(value = "order", required = false) String order){
        // List all the current courses in default order
        if (order == null) {
            return courseRepository.findAll();
            // List all the current courses in ascending order
        } else if (order == "ascending"){
            return courseRepository.findAllByOrderByNameOfCourseAsc();
            // List all the current courses in descending order
        } else if (order == "descending"){
            return courseRepository.findAllByOrderByNameOfCourseDesc();
        } else {
            throw new BadRequestException();
        }
    }

    @GetMapping("/courses/s/{registrationNumber}")
    List<Course> listAllCoursesOfAnInstitution(@PathVariable(value = "registrationNumber") String registrationNumber){
        Optional<Institution> institution = institutionRepository.findById(registrationNumber);
        if (institution.isEmpty()){
            throw new ResourceNotFoundException("Institution", "registrationNumber", registrationNumber);
        }
        return courseRepository.findCoursesByInstitutionOfferingCourse_RegistrationNumber(registrationNumber);
    }

    @GetMapping("/courses/{courseID}")
    Course getCourse(@PathVariable String courseID){
        return courseRepository.findById(courseID).orElseThrow(
                () -> new ResourceNotFoundException("Course", "courseID", courseID)
        );
    }

    @DeleteMapping("/courses/{courseID}")
    void deleteCourse(@PathVariable String courseID){
        courseRepository.deleteById(courseID);
    }

    // Add a new course
    @PostMapping("/courses")
    Course addNewCourse(@RequestBody Course newCourse){
        // Check if courses with the same courseID exist
        Optional<Course> course = courseRepository.findById(newCourse.getCourseID());
        if (course.isPresent()){
            throw new ResourceIDExistsException("Course", "courseID", newCourse.getCourseID());
        }
        List<Course> courseList = courseRepository.findCoursesByInstitutionOfferingCourse_RegistrationNumberAndNameOfCourse(
                newCourse.getInstitutionOfferingCourse().getRegistrationNumber(), newCourse.getNameOfCourse());
        if (!courseList.isEmpty()){
            throw new ResourceIDExistsException("Institution_Course", "InstitutionRegistrationNumber_NameOfCourse",
                    newCourse.getInstitutionOfferingCourse().getRegistrationNumber()+newCourse.getNameOfCourse());
        }
        return courseRepository.save(newCourse);
    }

}
