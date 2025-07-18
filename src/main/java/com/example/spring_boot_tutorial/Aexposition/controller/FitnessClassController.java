package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.AddMembersToFitnessClassDto;
import com.example.spring_boot_tutorial.Aexposition.dto.ConsultFitnessClassDTO;
import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateFitnessClassDto;
import com.example.spring_boot_tutorial.Aexposition.mapper.FitnessClassMapperService;
import com.example.spring_boot_tutorial.Bapplication.fitnessClass.*;
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
    UpdateFitnessClass updateFitnessClass;
    DeleteFitnessClass deleteFitnessClass;
    AddMembersToFitnessClass addMembersToFitnessClass;


    @GetMapping
    public ResponseEntity<List<ConsultFitnessClassDTO>> consultAll() {
        List<FitnessClass> fitnessClasses = consultAllFitnessClasses.consultAll();
        List<ConsultFitnessClassDTO> fitnessClassDtoList = fitnessClasses.stream().map(fitnessClassMapperService::mapToDto).toList();
        return new ResponseEntity<>(fitnessClassDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateUpdateFitnessClassDto dto) {
        FitnessClass fitnessClassToBeSaved = fitnessClassMapperService.mapToEntity(dto);
        createFitnessClass.createFitnessClass(fitnessClassToBeSaved);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //TODO make a new endpoint or adapt the existing one to return the dto of the updated entity(containing only
    // the name of the fitness class, duration and coach; only add members into dto if the members were updated (hint: should be in mapper)
    @PutMapping
    public ResponseEntity<Void> update(@RequestParam String fitnessClassId,
                                       @RequestBody CreateUpdateFitnessClassDto dto) {
        FitnessClass fitnessClassUpdated = fitnessClassMapperService.mapToEntity(dto);
        updateFitnessClass.update(fitnessClassUpdated, fitnessClassId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam String id) {
        deleteFitnessClass.delete(id);
        return ResponseEntity.ok("Fitness class deleted successfully");
    }

    @PostMapping(value = "/add-members")
    public ResponseEntity<Void> addMembers(@RequestBody AddMembersToFitnessClassDto dto) {
        addMembersToFitnessClass.addMembers(dto.getFitnessClassId(), dto.getMemberIds());
        return ResponseEntity.ok().build();
    }


}
