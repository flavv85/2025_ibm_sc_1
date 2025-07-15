package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.CoachDto;
import com.example.spring_boot_tutorial.Aexposition.dto.CreateUpdateCoachDto;
import com.example.spring_boot_tutorial.Aexposition.mapper.CoachMapperService;
import com.example.spring_boot_tutorial.Bapplication.coach.ConsultAllCoaches;
import com.example.spring_boot_tutorial.Bapplication.coach.CreateCoach;
import com.example.spring_boot_tutorial.Bapplication.coach.DeleteCoach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coach")
@AllArgsConstructor
public class CoachController {

    ConsultAllCoaches consultAllCoaches;
    CoachMapperService coachMapperService;
    CreateCoach createCoach;
    DeleteCoach deleteCoach;

    @GetMapping
    public ResponseEntity<List<CoachDto>> getAllCoach() {
        List<Coach> coaches = consultAllCoaches.consultAll();
        List<CoachDto> response = coaches
                .stream()
                .map(value ->coachMapperService.mapCoachFromEntityToDto(value))
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createCoach(@RequestBody CreateUpdateCoachDto dto) {
        Coach coach = coachMapperService.mapDtoToEntity(dto);
        createCoach.createCoach(coach);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteCoach(@RequestParam String coachId) {
        deleteCoach.deleteCoach(coachId);
        return ResponseEntity.ok("Coach delete successfully");
    }
}
