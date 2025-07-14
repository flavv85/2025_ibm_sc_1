package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.ConsultFitnessClassDTO;
import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateFitnessClassDto;
import com.example.spring_boot_tutorial.Aexposition.mapper.FitnessClassMapperService;
import com.example.spring_boot_tutorial.Bapplication.fitnessClass.ConsultAllFitnessClasses;
import com.example.spring_boot_tutorial.Bapplication.fitnessClass.CreateFitnessClass;
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

    ConsultAllFitnessClasses consultAllFitnessClasses;
    FitnessClassMapperService fitnessClassMapperService;
    CreateFitnessClass createFitnessClass;


    @GetMapping
    public ResponseEntity<List<ConsultFitnessClassDTO>> consultAll() {
        List<FitnessClass> fitnessClasses = consultAllFitnessClasses.consultAll();
        List<ConsultFitnessClassDTO> fitnessClassDtoList = fitnessClasses.stream().map(fitnessClassMapperService::mapFromDomain).toList();
        return new ResponseEntity<>(fitnessClassDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateUpdateFitnessClassDto dto){
        FitnessClass fitnessClassToBeSaved = fitnessClassMapperService.mapFromDtoToEntity(dto);
        createFitnessClass.createFitnessClass(fitnessClassToBeSaved);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
