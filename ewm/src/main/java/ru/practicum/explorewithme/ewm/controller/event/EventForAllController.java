package ru.practicum.explorewithme.ewm.controller.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.ewm.client.StatsClient;
import ru.practicum.explorewithme.ewm.dto.event.EventDto;
import ru.practicum.explorewithme.ewm.service.event.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/events")
public class EventForAllController {

    private final EventService eventService;
    private final StatsClient statsClient;

    @GetMapping("/{eventId}")
    public EventDto getAllByInitiatorId(@PathVariable Integer eventId, HttpServletRequest request) {
        log.info("Get event with id = {}.", eventId);
        statsClient.hitCall(request);
        return eventService.getEventByIdAndState(eventId);
    }

    @GetMapping
    public List<EventDto> getAllEventsWithFilter(
            @RequestParam(name = "text", required = false) String text,
            @RequestParam(name = "categories", required = false) List<Integer> categories,
            @RequestParam(name = "paid", required = false) Boolean paid,
            @RequestParam(name = "rangeStart", required = false) String rangeStart,
            @RequestParam(name = "rangeEnd", required = false) String rangeEnd,
            @RequestParam(name = "onlyAvailable", required = false, defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            HttpServletRequest request) {
        log.info("Get all events with filter");

        statsClient.hitCall(request);

        return eventService.getEventsByFilter(text, categories, paid, rangeStart, rangeEnd,
                onlyAvailable, sort, from, size);
    }
}
