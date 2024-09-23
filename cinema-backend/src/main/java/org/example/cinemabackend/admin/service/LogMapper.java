package org.example.cinemabackend.admin.service;

import org.example.cinemabackend.admin.dto.LogResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface LogMapper {

    List<LogResponse> mapRowsToLogResponses(ResultSet rs) throws SQLException;

    LogResponse mapRowToLogResponse(ResultSet rs) throws SQLException;
}
