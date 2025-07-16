package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.CoachDto;
import com.example.spring_boot_tutorial.Aexposition.mapper.CoachMapperService;
import com.example.spring_boot_tutorial.Bapplication.coaches.ConsultAllCoaches;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/coach")
public class CoachController {

    CoachMapperService coachMapperService;
    ConsultAllCoaches consultAllCoaches;

    @GetMapping
    public ResponseEntity<List<CoachDto>> consultAllCoaches(){
        List<Coach> coachesList = consultAllCoaches.consultAllCoaches();
        List<CoachDto> response = coachesList
                .stream()
                .map(coachMapperService::mapCoachFromEntityToDto)
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
