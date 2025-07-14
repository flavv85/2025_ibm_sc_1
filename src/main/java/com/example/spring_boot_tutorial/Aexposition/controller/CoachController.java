package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.CoachDto;
import com.example.spring_boot_tutorial.Aexposition.mapper.CoachMapperService;
import com.example.spring_boot_tutorial.Bapplication.members.ConsultAllCoaches;
import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/coach")
public class CoachController {
    @Autowired
    ConsultAllCoaches consultAllCoaches;

    @Autowired
    CoachMapperService coachMapperService;

    @GetMapping
    public ResponseEntity<List<CoachDto>> getAllCoaches(){
        List<Coach> coachList=consultAllCoaches.consultAllCoaches();
        List<CoachDto> coachDtoList=coachList
                .stream()
                .map(coach -> coachMapperService.coachToCoachDto(coach))
                .collect(Collectors.toList());
        return new ResponseEntity<>(coachDtoList, HttpStatus.OK);
    }
}
