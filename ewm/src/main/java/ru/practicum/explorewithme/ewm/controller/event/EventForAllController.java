package ru.practicum.explorewithme.ewm.controller.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.ewm.dto.event.EventDto;
import ru.practicum.explorewithme.ewm.service.event.EventService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/events")
public class EventForAllController {
    private final EventService eventService;
    @GetMapping("/{eventId}")
    public EventDto getAllByInitiatorId(@PathVariable Integer eventId) {
        log.info("Get event with id = {}.", eventId);
        return eventService.getEventByIdAndState(eventId);
    }

    @GetMapping
    public List<EventDto> getAllEventsWithFilter(
            @RequestParam(name = "text", required = false) String text,
            @RequestParam(name = "categories", required = false) List<Integer> categories,
            @RequestParam(name = "paid", required = false) Boolean paid,
            @RequestParam(name = "rangeStart", required = false) String rangeStart,
            @RequestParam(name = "rangeEnd", required = false) String rangeEnd,
            @RequestParam(name = "onlyAvailable", required = false) Boolean onlyAvailable,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "from", required = false) Integer from,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Get all events with filter");
        return null;
    }
}
