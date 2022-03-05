package com.gabrielowino.zerakicodingchallenge.controllers;

import com.gabrielowino.zerakicodingchallenge.exceptionhandlers.exceptions.BadRequestException;
import com.gabrielowino.zerakicodingchallenge.exceptionhandlers.exceptions.ResourceIDExistsException;
import com.gabrielowino.zerakicodingchallenge.exceptionhandlers.exceptions.ResourceNotFoundException;
import com.gabrielowino.zerakicodingchallenge.models.Institution;
import com.gabrielowino.zerakicodingchallenge.repositories.InstitutionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class InstitutionController {

    private final InstitutionRepository institutionRepository;

    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    // List all the current institutions
    @GetMapping("/institutions")
    List<Institution> listAll(@RequestParam(value = "order", required = false) String order){
        // List all the current institutions in default order
        if (order == null) {
            return institutionRepository.findAll();
        // List all the current institutions in ascending order
        } else if (order == "ascending"){
            return institutionRepository.findAllByOrderByNameOfInstitutionAsc();
        // List all the current institutions in descending order
        } else if (order == "descending"){
            return institutionRepository.findAllByOrderByNameOfInstitutionDesc();
        } else {
            throw new BadRequestException();
        }
    }

    // Add a new institution
    @PostMapping("/institutions")
    Institution addNewInstitution(@RequestBody Institution newInstitution){
        // Check if institutions with the same registration number or name exist
        Optional<Institution> institution = institutionRepository.findById(newInstitution.getRegistrationNumber());
        if (!institution.isEmpty()){
            throw new ResourceIDExistsException("Institution", "registrationNumber", newInstitution.getRegistrationNumber());
        }
        List<Institution> institutionList = institutionRepository.findByNameOfInstitution(
                newInstitution.getNameOfInstitution());
        if (!institutionList.isEmpty()){
            throw new ResourceIDExistsException("Institution", "InstitutionName", newInstitution.getNameOfInstitution());
        }
        return institutionRepository.save(newInstitution);
    }

    @PutMapping("/institutions/{registrationNumber}")
    Institution editNameOfInstitution(@PathVariable String registrationNumber, @RequestParam String nameOfInstitution) {
        Optional<Institution> institutions = institutionRepository.findById(registrationNumber);
        if (institutions.isPresent()) {
            List<Institution> similarNamedInstitutions = institutionRepository.findByNameOfInstitution(nameOfInstitution);
            if (similarNamedInstitutions.isEmpty()) {
                Institution institution = institutionRepository.getById(registrationNumber);
                institution.setNameOfInstitution(nameOfInstitution);
                return institutionRepository.save(institution);
            } else {
                throw new ResourceIDExistsException("Institution", "InstitutionName", nameOfInstitution);
            }
        } else {
            throw new ResourceNotFoundException("Institution", "registrationNumber", registrationNumber);
        }
    }

    @GetMapping("/institutions/{registrationNumber}")
    Institution getInstitution(@PathVariable String registrationNumber){
        return institutionRepository.findById(registrationNumber).orElseThrow(
                () -> new ResourceNotFoundException("Institution", "registrationNumber", registrationNumber)
        );
    }

    @DeleteMapping("/institutions/{registrationNumber}")
    void deleteInstitution(@PathVariable String registrationNumber){
        institutionRepository.deleteById(registrationNumber);
    }

}
