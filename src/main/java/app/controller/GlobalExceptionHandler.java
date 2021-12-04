package app.controller;
import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import app.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(HttpServletRequest request, Exception ex){
        logger.info("EntityNotFoundException Occured:: URL="+request.getRequestURL());
        Application.ApiError apiError =
            new Application.ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getMessage());
        return new ResponseEntity<Object>(
            apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException(HttpServletRequest request, Exception ex){
        logger.info("EntityNotFoundException Occured:: URL="+request.getRequestURL());
        Application.ApiError apiError =
            new Application.ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getMessage());
        return new ResponseEntity<Object>(
            apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOException(HttpServletRequest request, Exception ex){
        logger.info("EntityNotFoundException Occured:: URL="+request.getRequestURL());
        Application.ApiError apiError =
            new Application.ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getMessage());
        return new ResponseEntity<Object>(
            apiError, new HttpHeaders(), apiError.getStatus());
    }

}
