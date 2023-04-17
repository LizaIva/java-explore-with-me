package ru.practicum.explorewithme.statistics.service;

import ru.practicum.explorewithme.statistics.dto.StatisticsDto;
import ru.practicum.explorewithme.statistics.dto.ViewStatsDto;

import java.util.List;

public interface StatisticsService {
    StatisticsDto put(StatisticsDto statisticsDto);

    List<ViewStatsDto> get(String start, String end, List<String> uris, Boolean unique);
}
