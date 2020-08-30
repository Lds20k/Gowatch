package com.lucas.gowatch.controller.handler;

import com.lucas.gowatch.controller.model.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({FileNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericResponse handlerFileNotFound(final FileNotFoundException e){
        return new GenericResponse("Video not found!");
    }

}
