package ru.practicum.explorewithme.statistics.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.statistics.dto.StatisticsDto;
import ru.practicum.explorewithme.statistics.dto.ViewStatsDto;
import ru.practicum.explorewithme.statistics.model.Statistics;
import ru.practicum.explorewithme.statistics.model.ViewStats;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StatisticsMapper {
    public StatisticsDto mapToStatisticsDto(Statistics statistics) {
        return StatisticsDto.builder()
                .app(statistics.getApp())
                .uri(statistics.getUri())
                .ip(statistics.getIp())
                .created(statistics.getCreated())
                .build();
    }

    public Statistics mapToStatistics(StatisticsDto statisticsDto) {
        return Statistics.builder()
                .app(statisticsDto.getApp())
                .uri(statisticsDto.getUri())
                .ip(statisticsDto.getIp())
                .created(statisticsDto.getCreated())
                .build();
    }

    public ViewStatsDto mapToViewStatsDto(ViewStats viewStats) {
        return ViewStatsDto.builder()
                .app(viewStats.getApp())
                .uri(viewStats.getUri())
                .hits(viewStats.getHits())
                .build();
    }

    public List<ViewStatsDto> mapToViewStatsDtos(List<ViewStats> viewStatsList) {
        List<ViewStatsDto> viewStatsDtoList = new ArrayList<>();
        for (ViewStats viewStats : viewStatsList) {
            ViewStatsDto ViewStatsDto = mapToViewStatsDto(viewStats);
            viewStatsDtoList.add(ViewStatsDto);
        }
        return viewStatsDtoList;
    }
}
