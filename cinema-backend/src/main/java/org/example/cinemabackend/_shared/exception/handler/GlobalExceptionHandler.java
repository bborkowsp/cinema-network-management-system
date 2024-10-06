package org.example.cinemabackend._shared.exception.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.List;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
class GlobalExceptionHandler {
    private final ExceptionHandlerUtil exceptionHandlerUtil;

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Map<String, List<String>>> handleIllegalStateException(MaxUploadSizeExceededException exception) {
        return exceptionHandlerUtil.createResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
