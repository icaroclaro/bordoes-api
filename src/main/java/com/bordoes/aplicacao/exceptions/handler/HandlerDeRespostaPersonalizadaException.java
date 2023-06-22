package com.bordoes.aplicacao.exceptions.handler;

import com.bordoes.aplicacao.exceptions.MyNotFoundException;
import com.bordoes.aplicacao.exceptions.RespostaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class HandlerDeRespostaPersonalizadaException {

    @ExceptionHandler(MyNotFoundException.class)
    public final ResponseEntity<RespostaException> handleNotFoundExceptions(
            Exception ex, WebRequest request) {

        RespostaException respostaException = new RespostaException(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(respostaException, HttpStatus.NOT_FOUND);
    }
}
