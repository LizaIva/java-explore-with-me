package ru.practicum.explorewithme.statistics.storage;

import ru.practicum.explorewithme.statistics.model.Statistics;
import ru.practicum.explorewithme.statistics.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsStorage {
    Statistics put(Statistics statistics);

    List<ViewStats> get(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
