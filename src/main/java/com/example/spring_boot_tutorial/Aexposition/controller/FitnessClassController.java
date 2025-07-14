package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.FitnessClassDTO;
import com.example.spring_boot_tutorial.Aexposition.mapper.FitnessClassMapperService;
import com.example.spring_boot_tutorial.Bapplication.fitnessclass.ConsultAllFitnessClasses;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fitness")
public class FitnessClassController {

    @Autowired
    ConsultAllFitnessClasses consultAllFitnessClasses;

    @Autowired
    FitnessClassMapperService fitnessclassMapperService;

    @GetMapping
    public ResponseEntity<List<FitnessClassDTO>> getFitnessClass() {
        List<FitnessClass> fitnessClasses = consultAllFitnessClasses.consultAll();
        List<FitnessClassDTO> response = fitnessClasses
                .stream()
                .map(value -> fitnessclassMapperService.mapFitnessClassFromEntityToDTO(value))
                .toList();


            return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
