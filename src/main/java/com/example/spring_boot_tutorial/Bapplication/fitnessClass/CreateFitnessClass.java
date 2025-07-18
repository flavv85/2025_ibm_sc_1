package com.example.spring_boot_tutorial.Bapplication.fitnessClass;

import com.example.spring_boot_tutorial.Ddomain.exceptions.BusinessException;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateFitnessClass {
    FitnessClasses fitnessClasses;

    public void createFitnessClass(FitnessClass fitnessClass) {
        //todo validare please check if the associated coach really exists in the db, if not throw an error
//        already done in the mapperService

        //todo validate if no if members is < 3 or > 8 throw an error saying that it needs to be between 3 and 8 members
        int numberOfMembers = fitnessClass.getMembers().size();
        if (numberOfMembers < 3 || numberOfMembers > 8) {
            throw new BusinessException(String.format("Number of members must be between 3 and 8 and actual number is %s", numberOfMembers));
        }
        fitnessClasses.saveFitnessClass(fitnessClass);
    }
}