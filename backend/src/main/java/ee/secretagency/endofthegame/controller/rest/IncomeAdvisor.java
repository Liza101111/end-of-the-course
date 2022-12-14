package ee.secretagency.endofthegame.controller.rest;

import ee.secretagency.endofthegame.dto.ErrorInfo;
import ee.secretagency.endofthegame.exception.IncomeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.ZonedDateTime;

@RestControllerAdvice
@Slf4j
public class IncomeAdvisor {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IncomeNotFoundException.class)
    public ErrorInfo handleIncomeNotFoundException(IncomeNotFoundException e) {
        log.debug("handing not found incomes");
        log.warn("income not found!", e);
        return ErrorInfo.builder()
                .status(404)
                .error("income not found!")
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now())
                .path(ServletUriComponentsBuilder.fromCurrentRequest().toUriString()) //TODO: extract only path
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorInfo handleValidationException(MethodArgumentNotValidException e){
        log.warn("Validation Exception", e);

        return ErrorInfo.builder()
                .status(400)
                .error("Validation failed")
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now())
                .path(ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                .build();
    }
}
