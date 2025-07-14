package com.example.spring_boot_tutorial.Bapplication.fitnessclass;

import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClass;
import com.example.spring_boot_tutorial.Ddomain.fitnessclass.FitnessClasses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateFitnessClass {

    FitnessClasses fitnessClasses;

    public void update(FitnessClass fitnessClass, String fitnessClassId) {
        //todo the id should be set in the MapperService -> public FitnessClass mapToEntity(...),
        // and the fitnessClassId should not be passed as a param anymore in this class
        fitnessClass.setId(fitnessClassId);

        //TODO !add a validate method if coach id is missing or not existing
        //TODO !add a validate method to check if startTime < endTime and duration length is valid (see class method)
        //TODO !!!create a validation to check and none the parameters of the fitness class has changed and inform the user!
        //TODO !create custom errors
        //TODO !!create a single private method to contain all validations
        fitnessClasses.updateFitnessClass(fitnessClass);
    }

}
