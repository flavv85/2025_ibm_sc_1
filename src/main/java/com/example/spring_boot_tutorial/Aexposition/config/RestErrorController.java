package com.example.spring_boot_tutorial.Aexposition.config;

import com.example.spring_boot_tutorial.Ddomain.Exception.BussinessException;
import com.example.spring_boot_tutorial.Ddomain.Exception.UnknownObjectException;
import com.example.spring_boot_tutorial.Ddomain.Exception.CoachDeletionException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestErrorController {

    @ExceptionHandler
    public ResponseEntity<CustomErrorMessage> handleUnknownObjectException(UnknownObjectException exception) {
        CustomErrorMessage customErrorMessage = CustomErrorMessage.builder()
                .title("Unknown object exception")
                .details(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return ResponseEntity
                .status(customErrorMessage.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(customErrorMessage);
    }

    @ExceptionHandler
    public  ResponseEntity<CustomErrorMessage> handleCoachDeletionException(CoachDeletionException exception) {
        CustomErrorMessage customErrorMessage = CustomErrorMessage.builder()
                .title("Coach deletion exception")
                .details(exception.getMessage())
                .status(HttpStatus.CONFLICT)
                .build();

        return ResponseEntity
                .status(customErrorMessage.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(customErrorMessage);

    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorMessage> handleBussinessException(BussinessException exception) {
        CustomErrorMessage customErrorMessage = CustomErrorMessage.builder()
                .title("Bussiness exception")
                .details(exception.getMessage())
                .status(HttpStatus.NOT_ACCEPTABLE)
                .build();

        return ResponseEntity
                .status(customErrorMessage.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(customErrorMessage);
    }


}
