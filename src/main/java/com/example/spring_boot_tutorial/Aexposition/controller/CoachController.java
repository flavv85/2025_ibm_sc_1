package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.CoachDto;
import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateCoachDto;
import com.example.spring_boot_tutorial.Aexposition.mapper.CoachMapperService;
import com.example.spring_boot_tutorial.Bapplication.coaches.ConsultAllCoaches;
import com.example.spring_boot_tutorial.Bapplication.coaches.CreateCoach;
import com.example.spring_boot_tutorial.Bapplication.coaches.DeleteCoach;
import com.example.spring_boot_tutorial.Bapplication.coaches.UpdateCoach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/coach")
public class CoachController {

    CoachMapperService coachMapperService;
    ConsultAllCoaches consultAllCoaches;
    CreateCoach createCoach;
    UpdateCoach updateCoach;
    DeleteCoach deleteCoach;

    @GetMapping
    public ResponseEntity<List<CoachDto>> consultAllCoaches(){
        List<Coach> coachesList = consultAllCoaches.consultAllCoaches();
        List<CoachDto> response = coachesList
                .stream()
                .map(coachMapperService::mapCoachFromEntityToDto)
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateUpdateCoachDto dto) {
        Coach coachToBeSaved = coachMapperService.mapFromDtoToEntity(dto);
        createCoach.createCoach(coachToBeSaved);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestParam String coachId,
                                            @RequestBody CreateUpdateCoachDto dto) {
        Coach coachToBeUpdated = coachMapperService.mapFromDtoToEntity(dto);
        updateCoach.updateCoach(coachToBeUpdated, coachId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam String id) {
        deleteCoach.deleteCoach(id);
        return ResponseEntity.ok("Coach deleted successfully");
    }
}
