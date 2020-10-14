package com.ajasoft.jackpot.jackpotcore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.Locale;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handler(Exception ex) {
        log.error("error", ex);
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public String handlerValidation(WebExchangeBindException ex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        ex.getAllErrors().forEach(e -> sb.append(messageSource.getMessage(e.getCode(), null, Locale.US)).append(", "));
        ex.getAllErrors().forEach(e -> sb1.append(e.getDefaultMessage()).append(", "));
        log.error("validationError - defaultMsg: {}, responseMsg: {} ", sb1.toString(), sb.toString());
        return sb.toString();
    }



}
