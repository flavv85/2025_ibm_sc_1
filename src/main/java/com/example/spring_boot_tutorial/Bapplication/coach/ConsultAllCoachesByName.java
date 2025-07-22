package com.example.spring_boot_tutorial.Bapplication.coach;

import com.example.spring_boot_tutorial.Ddomain.coach.Coach;
import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;

import java.util.List;

public class ConsultAllCoachesByName {
    Coaches coaches;
    public List<Coach> consultByName(String name){return coaches.getAllCoachesByName(name);}
}
