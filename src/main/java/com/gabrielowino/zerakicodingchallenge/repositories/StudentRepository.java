package com.gabrielowino.zerakicodingchallenge.repositories;

import com.gabrielowino.zerakicodingchallenge.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StudentRepository extends JpaRepository<Student, String> {
}
