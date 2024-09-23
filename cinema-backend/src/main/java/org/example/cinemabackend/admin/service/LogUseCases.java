package org.example.cinemabackend.admin.service;

import org.example.cinemabackend.admin.dto.LogResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LogUseCases {
    List<LogResponse> getLogs(Pageable pageable);

    Long countLogRows();
}
