package ru.practicum.explorewithme.ewm.controller.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.ewm.dto.event.CreateEventDto;
import ru.practicum.explorewithme.ewm.dto.event.EventDto;
import ru.practicum.explorewithme.ewm.service.event.EventService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin/events")
public class EventForAdminController {
    private final EventService eventService;

    @GetMapping
    public List<EventDto> getAllByInitiatorsStatesCategories(
            @RequestParam(name = "users", required = false) List<Integer> users,
            @RequestParam(name = "states", required = false) List<String> states,
            @RequestParam(name = "categories", required = false) List<Integer> categories,
            @RequestParam(name = "rangeStart", required = false) String rangeStart,
            @RequestParam(name = "rangeEnd", required = false) String rangeEnd,
            @RequestParam(name = "from", required = false) Integer from,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Get all events by usersId, states and categories");
        return eventService.getEventsByInitiatorsStatesCategories(users, states, categories,
                rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    public EventDto updateEventFromAdmin(@PathVariable Integer eventId,
                                         @RequestBody @Valid CreateEventDto createEventDto) {
        log.info("Update event with id = {} from admin", eventId);
        return eventService.updateEventFromAdmin(eventId, createEventDto);
    }
}
