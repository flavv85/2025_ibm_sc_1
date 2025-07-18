package com.example.spring_boot_tutorial.Aexposition.controller;

import com.example.spring_boot_tutorial.Aexposition.dto.ConsultCoachDto;
import com.example.spring_boot_tutorial.Aexposition.mapper.CoachMapperService;
import com.example.spring_boot_tutorial.Bapplication.coach.ConsultAllCoaches;
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

    @GetMapping
    public ResponseEntity<List<ConsultCoachDto>> consultAll() {
        List<Coach> coaches = consultAllCoaches.consultAll();
        List<ConsultCoachDto> coachDtoList = coaches.stream().map(coachMapperService::mapFromDomain).toList();
        return new ResponseEntity<>(coachDtoList, HttpStatus.OK);
    }

}
