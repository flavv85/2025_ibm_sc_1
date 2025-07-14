package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.ConsultCoachDto;
import com.example.spring_boot_tutorial.Aexposition.mapper.CoachMapperService;
import com.example.spring_boot_tutorial.Bapplication.coach.ConsultAllCoaches;
import com.example.spring_boot_tutorial.Bapplication.coach.CreateCoach;
import com.example.spring_boot_tutorial.Bapplication.coach.DeleteCoach;
import com.example.spring_boot_tutorial.Bapplication.coach.UpdateCoach;
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

    ConsultAllCoaches consultAllCoaches;
    CoachMapperService coachMapperService;
    CreateCoach createCoach;
    UpdateCoach updateCoach;
    DeleteCoach deleteCoach;

    @GetMapping
    public ResponseEntity<List<ConsultCoachDto>> consultAll() {
        List<Coach> coaches = consultAllCoaches.consultAll();
        List<ConsultCoachDto> coachDtoList = coaches.stream().map(coachMapperService::mapFromDomain).toList();
        return new ResponseEntity<>(coachDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Coach> create(@RequestBody ConsultCoachDto dto) {
        Coach coachToBeSaved = coachMapperService.mapToEntity(dto);
        createCoach.createCoach(coachToBeSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(coachToBeSaved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody ConsultCoachDto dto) {
        System.out.println("COACH ID PRIMIT: " + id);
        Coach coach = coachMapperService.mapToEntity(dto);
        updateCoach.updateCoach(id, coach);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleteCoach.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
