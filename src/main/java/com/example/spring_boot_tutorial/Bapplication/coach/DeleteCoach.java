package com.example.spring_boot_tutorial.Bapplication.coach;

import com.example.spring_boot_tutorial.Ddomain.coach.Coaches;
import com.example.spring_boot_tutorial.Ddomain.exceptions.UnknownObjectException;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Transactional
@Service
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal=true)
public class DeleteCoach {
    @Autowired
    Coaches coaches;
    public void delete(String id){
        checkIfCoachExistsInDb(id);
        coaches.delete(id);
    }
    private void checkIfCoachExistsInDb(String id){
        coaches.getCoachById(id).orElseThrow(()-> new UnknownObjectException(String.format("Coach with id %s was noty found",id)));
    }
}
