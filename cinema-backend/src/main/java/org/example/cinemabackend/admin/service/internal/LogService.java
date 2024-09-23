package org.example.cinemabackend.admin.service.internal;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.admin.dto.LogResponse;
import org.example.cinemabackend.admin.service.LogMapper;
import org.example.cinemabackend.admin.service.LogUseCases;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class LogService implements LogUseCases {
    private static final String BASE_QUERY = "SELECT timestamp, level, logger, message FROM logs";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM logs";
    private final JdbcTemplate jdbcTemplate;
    private final LogMapper logMapper;

    @Override
    public List<LogResponse> getLogs(Pageable pageable) {
        final var query = prepareGetLogsPageQuery(pageable);
        return jdbcTemplate.query(query, logMapper::mapRowsToLogResponses);
    }

    @Override
    public Long countLogRows() {
        return jdbcTemplate.queryForObject(COUNT_QUERY, Long.class);
    }

    private String prepareGetLogsPageQuery(Pageable pageable) {
        String sortClause = pageable.getSort().isSorted()
                ? " ORDER BY " + pageable.getSort().toString().replace(":", "")
                : " ORDER BY timestamp DESC";
        String limitOffsetClause = " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset();
        return BASE_QUERY + sortClause + limitOffsetClause;
    }
}
