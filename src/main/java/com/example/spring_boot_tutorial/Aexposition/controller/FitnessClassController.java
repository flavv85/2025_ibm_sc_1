package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateFitnessClassDto;
import com.example.spring_boot_tutorial.Aexposition.dto.FitnessClassDto;
import com.example.spring_boot_tutorial.Aexposition.mapper.FitnessClassMapperService;
import com.example.spring_boot_tutorial.Bapplication.fitnessClasses.ConsultAllFitnessClasses;
import com.example.spring_boot_tutorial.Bapplication.fitnessClasses.CreateFitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/fitness-class")
public class FitnessClassController {

    FitnessClassMapperService fitnessClassMapperService;
    ConsultAllFitnessClasses consultAllFitnessClasses;
    CreateFitnessClass createFitnessClass;

    @GetMapping
    public ResponseEntity<List<FitnessClassDto>> consultAllFitnessClasses() {
        List<FitnessClass> fitnessClasses = consultAllFitnessClasses.consultAll();
        List<FitnessClassDto> fitnessClassDtoList = fitnessClasses.stream()
                .map(fitnessClassMapperService::mapFitnessClassFromEntityToDto)
                .toList();
        return new ResponseEntity<>(fitnessClassDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateUpdateFitnessClassDto dto){
        FitnessClass fitnessClassToBeSaved = fitnessClassMapperService.mapFromDtoToEntity(dto);
        createFitnessClass.createFitnessClass(fitnessClassToBeSaved);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
