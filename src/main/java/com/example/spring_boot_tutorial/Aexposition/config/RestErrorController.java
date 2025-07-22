package com.example.spring_boot_tutorial.Aexposition.config;

import com.example.spring_boot_tutorial.Ddomain.exceptions.BusinessException;
import com.example.spring_boot_tutorial.Ddomain.exceptions.UnknownObjectException;
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
    public ResponseEntity<CustomErrorMessage> handleBusinessException(BusinessException exception) {
        CustomErrorMessage customErrorMessage = CustomErrorMessage.builder()
                .title("Business exception")
                .details(exception.getMessage())
                .status(HttpStatus.NOT_ACCEPTABLE)
                .build();

        return ResponseEntity
                .status(customErrorMessage.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(customErrorMessage);
    }

}
