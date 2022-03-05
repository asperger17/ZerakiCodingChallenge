package com.gabrielowino.zerakicodingchallenge.repositories;

import com.gabrielowino.zerakicodingchallenge.models.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, String> {

    List<Institution> findByRegistrationNumberOrNameOfInstitution(
            String registrationNumber, String nameOfInstitution);

    List<Institution> findAllByOrderByNameOfInstitutionAsc();

    List<Institution> findAllByOrderByNameOfInstitutionDesc();

    List<Institution> findByNameOfInstitution(String nameOfInstitution);
}
