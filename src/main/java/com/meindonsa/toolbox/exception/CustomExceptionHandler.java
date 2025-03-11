package com.meindonsa.toolbox.exception;

import com.meindonsa.toolbox.utils.ErrorMessages;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity(
                new ErrorResponse(ErrorMessages.ERR_TECHNICAL, processError(ex)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleRecordNotFoundException(
            RecordNotFoundException ex, WebRequest request) {
        return new ResponseEntity(
                new ErrorResponse(ErrorMessages.ERR_UNKNOWN, processError(ex)),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<Object> handleUnauthoriedException(
            UnauthorizedException ex, WebRequest request) {
        return new ResponseEntity(
                new ErrorResponse(ErrorMessages.ERR_UNAUTHORIZED, processError(ex)),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public final ResponseEntity<Object> handleForbiddenException(
            ForbiddenException ex, WebRequest request) {
        return new ResponseEntity(
                new ErrorResponse(ErrorMessages.ERR_FORBIDDEN, processError(ex)),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(FunctionalException.class)
    public final ResponseEntity<Object> handleInvalidInputException(
            FunctionalException ex, WebRequest request) {
        return new ResponseEntity(
                new ErrorResponse(ErrorMessages.ERR_INVALID_OPERATION, processError(ex)),
                HttpStatus.NOT_ACCEPTABLE);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        return new ResponseEntity(
                new ErrorResponse(ErrorMessages.ERR_VALIDATION_FAILED, processError(ex)),
                HttpStatus.BAD_REQUEST);
    }

    protected List<String> processError(Exception ex) {
        List<String> details = new ArrayList<>();
        if (ex instanceof MethodArgumentNotValidException) {
            for (ObjectError error : ((BindException) ex).getBindingResult().getAllErrors()) {
                String fieldName = ((FieldError) error).getField();
                details.add(fieldName + " " + error.getDefaultMessage());
            }
        } else {
            details.add(ex.getLocalizedMessage());
        }
        log.error("{}", details);
        log.error("{}", ex);
        return details;
    }
}
