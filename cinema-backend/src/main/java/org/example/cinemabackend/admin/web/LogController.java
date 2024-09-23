package org.example.cinemabackend.admin.web;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend._shared.dto.RestResponsePage;
import org.example.cinemabackend.admin.dto.LogResponse;
import org.example.cinemabackend.admin.service.LogUseCases;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/logs")
@RequiredArgsConstructor
class LogController {
    private final LogUseCases logUseCases;

    @GetMapping
    ResponseEntity<RestResponsePage<LogResponse>> getMovies(Pageable pageable) {
        final var logs = logUseCases.getLogs(pageable);
        final var totalElements = logUseCases.countLogRows();
        return ResponseEntity.ok(new RestResponsePage<>(logs, pageable, totalElements));
    }
}
