package org.example.cinemabackend._shared.exception.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor
class RestResponseEntityExceptionHandler {
    private final ExceptionHandlerUtil exceptionHandlerUtil;

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, List<String>>> handleIllegalStateException(IllegalStateException exception) {
        return exceptionHandlerUtil.createResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, List<String>>> handleIllegalArgumentException(IllegalArgumentException exception) {
        return exceptionHandlerUtil.createResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, List<String>>> handleNoSuchElementException(NoSuchElementException exception) {
        return exceptionHandlerUtil.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
