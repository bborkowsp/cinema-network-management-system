package org.example.cinemabackend._shared.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
class ExceptionHandlerUtil {
    public static final String RESPONSE_ERRORS_KEY = "errors";

    public ResponseEntity<Map<String, List<String>>> createResponse(String message, HttpStatus status) {
        return createResponse(List.of(message), status);
    }

    private ResponseEntity<Map<String, List<String>>> createResponse(List<String> errors, HttpStatus status) {
        final var content = Map.of(RESPONSE_ERRORS_KEY, errors);
        return new ResponseEntity<>(content, new HttpHeaders(), status);
    }
}
