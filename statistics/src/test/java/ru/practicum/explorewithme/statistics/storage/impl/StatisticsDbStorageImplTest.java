package ru.practicum.explorewithme.statistics.storage.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.practicum.explorewithme.statistics.dto.StatisticsDto;
import ru.practicum.explorewithme.statistics.dto.ViewStatsDto;
import ru.practicum.explorewithme.statistics.model.Statistics;
import ru.practicum.explorewithme.statistics.repository.StatisticsRepository;
import ru.practicum.explorewithme.statistics.service.StatisticsService;
import ru.practicum.explorewithme.statistics.utils.StatisticsMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class StatisticsDbStorageImplTest {
    private final StatisticsService statisticsService;
    private final JdbcTemplate jdbcTemplate;

    private final StatisticsRepository statisticsRepository;

    private final StatisticsMapper statisticsMapper;

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("DELETE FROM STATISTICS");
    }

    LocalDateTime now = LocalDateTime.now();
    private static final DateTimeFormatter START_END_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String APP = "ewm-main-service";
    private static final String URI = "/events/1";
    private static final String IP = "192.163.0.1";

    @Test
    void put() {
        StatisticsDto statisticsDto = statisticsService.put(StatisticsDto.builder()
                .app(APP)
                .uri(URI)
                .ip(IP)
                .timestamp(now)
                .build());

        List<Statistics> actualStatistics = statisticsRepository.findAll();
        StatisticsDto actualStatisticsDto = statisticsMapper.mapToStatisticsDto(actualStatistics.get(0));

        assertEquals(statisticsDto.getApp(), actualStatisticsDto.getApp());
        assertEquals(statisticsDto.getUri(), actualStatisticsDto.getUri());
        assertEquals(statisticsDto.getIp(), actualStatisticsDto.getIp());
    }

    @Test
    void get() {
        StatisticsDto statisticsDto = statisticsService.put(StatisticsDto.builder()
                .app(APP)
                .uri(URI)
                .ip(IP)
                .timestamp(now)
                .build());

        String start = now.minusHours(1).format(START_END_DATE_FORMATTER);
        String end = now.plusHours(1).format(START_END_DATE_FORMATTER);

        List<ViewStatsDto> actualStatistics = statisticsService.get(
                URLEncoder.encode(start, StandardCharsets.UTF_8),
                URLEncoder.encode(end, StandardCharsets.UTF_8),
                null,
                true
        );

        assertNotNull(actualStatistics);

        ViewStatsDto actual = actualStatistics.get(0);

        assertEquals(statisticsDto.getApp(), actual.getApp());
        assertEquals(statisticsDto.getUri(), actual.getUri());
    }
}