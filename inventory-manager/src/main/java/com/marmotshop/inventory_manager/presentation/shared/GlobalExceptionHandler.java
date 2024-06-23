package com.marmotshop.inventory_manager.presentation.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseEntity> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        List<ErrorEntity> errors = ex.getConstraintViolations().stream()
                .map(violation -> ErrorEntity.builder()
                        .field(violation.getPropertyPath().toString())
                        .message(violation.getMessage())
                        .build())
                .collect(Collectors.toList());

        ErrorResponseEntity errorResponseEntity = ErrorResponseEntity.builder()
                .errors(errors)
                .build();

        return new ResponseEntity<>(errorResponseEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseEntity> handleDuplicatedExceptions(DataIntegrityViolationException ex) {

        ErrorEntity errorEntity = ErrorEntity.builder()
                .field("DataIntegrityViolation")
                .message(ex.getMessage())
                .build();
        ErrorResponseEntity errorResponseEntity = ErrorResponseEntity.builder()
                .errors(List.of(errorEntity))
                .build();

        return new ResponseEntity<>(errorResponseEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseEntity> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<ErrorEntity> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            ErrorEntity errorEntity = new ErrorEntity();
            errorEntity.setField(fieldError.getField());
            errorEntity.setMessage(fieldError.getField() + " " +
                    fieldError.getDefaultMessage());

            errors.add(errorEntity);
        }

        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity();
        errorResponseEntity.setErrors(errors);

        return new ResponseEntity<>(errorResponseEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseEntity> handleNotFoundException(EntityNotFoundException ex) {
        ErrorEntity errorEntity = ErrorEntity.builder()
                .field("resource")
                .message(ex.getMessage())
                .build();
        ErrorResponseEntity errorResponseEntity = ErrorResponseEntity.builder()
                .errors(List.of(errorEntity))
                .build();
        return new ResponseEntity<>(errorResponseEntity, HttpStatus.NOT_FOUND);
    }

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseEntity> handleTypeMismatchExceptions(MethodArgumentTypeMismatchException ex) {
        ErrorEntity errorEntity = ErrorEntity.builder()
                .field(ex.getName())
                .message("Invalid value: " + ex.getValue())
                .build();
        ErrorResponseEntity errorResponseEntity = ErrorResponseEntity.builder()
                .errors(List.of(errorEntity))
                .build();
        return new ResponseEntity<>(errorResponseEntity, HttpStatus.BAD_REQUEST);
    }

    // FIXME: just for trying now
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseEntity> handleAllExceptions(Exception ex) {

        List<ErrorEntity> errors = new ArrayList<>();
        for (FieldError fieldError : ((BindException) ex).getBindingResult().getFieldErrors()) {
            ErrorEntity errorEntity = new ErrorEntity();
            errorEntity.setField(fieldError.getField());
            errorEntity.setMessage(fieldError.getField() + " " +
                    fieldError.getDefaultMessage());

            errors.add(errorEntity);
        }

        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity();
        errorResponseEntity.setErrors(errors);

        return new ResponseEntity<>(errorResponseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
