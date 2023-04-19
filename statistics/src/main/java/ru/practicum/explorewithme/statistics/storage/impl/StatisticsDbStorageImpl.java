package ru.practicum.explorewithme.statistics.storage.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.statistics.model.Statistics;
import ru.practicum.explorewithme.statistics.model.ViewStats;
import ru.practicum.explorewithme.statistics.repository.StatisticsRepository;
import ru.practicum.explorewithme.statistics.storage.StatisticsStorage;

import java.time.LocalDateTime;
import java.util.List;

@Component("statisticsDbStorageImpl")
@RequiredArgsConstructor
public class StatisticsDbStorageImpl implements StatisticsStorage {
    private final StatisticsRepository statisticsRepository;

    @Override
    public Statistics put(Statistics statistics) {
        return statisticsRepository.saveAndFlush(statistics);
    }

    @Override
    public List<ViewStats> get(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (unique) {
            return statisticsRepository.findStatisticsByTimeAndUnique(start, end, uris == null ? 1 : 0, uris);
        } else {
            return statisticsRepository.findStatisticsByTime(start, end, uris == null ? 1 : 0, uris);
        }
    }
}
