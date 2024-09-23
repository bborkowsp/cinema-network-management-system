package org.example.cinemabackend.admin.service.internal;

import org.example.cinemabackend.admin.dto.LogResponse;
import org.example.cinemabackend.admin.service.LogMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
class LogMapperService implements LogMapper {

    @Override
    public List<LogResponse> mapRowsToLogResponses(ResultSet rs) throws SQLException {
        List<LogResponse> logResponses = new ArrayList<>();
        while (rs.next()) {
            logResponses.add(mapRowToLogResponse(rs));
        }
        return logResponses;
    }

    @Override
    public LogResponse mapRowToLogResponse(ResultSet rs) throws SQLException {
        return new LogResponse(
                rs.getTimestamp("timestamp"),
                rs.getString("level"),
                rs.getString("logger"),
                rs.getString("message")
        );
    }
}

