package com.gabrielowino.zerakicodingchallenge.repositories;

import com.gabrielowino.zerakicodingchallenge.models.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, String> {
}
