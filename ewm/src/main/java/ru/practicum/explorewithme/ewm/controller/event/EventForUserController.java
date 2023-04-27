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
@RequestMapping(path = "/users/{userId}/events")
public class EventForUserController {

    private final EventService eventService;

    @PostMapping
    public EventDto create(@RequestBody @Valid CreateEventDto createEventDto,
                           @PathVariable Integer userId) {
        log.info("Create event from user with id = {}", userId);
        return eventService.put(createEventDto, userId);
    }

    @GetMapping
    public List<EventDto> getAllByInitiatorId(@PathVariable Integer userId,
                                              @RequestParam(name = "from", required = false) Integer from,
                                              @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Get all events from userId = {}", userId);
        return eventService.getAllByInitiatorId(userId, from, size);
    }

    @GetMapping("/{eventId}")
    public EventDto getAllByInitiatorId(@PathVariable Integer userId,
                                        @PathVariable Integer eventId) {
        log.info("Get event with id = {} from userId = {}", eventId, userId);
        return eventService.getEventByInitiatorIdAndEventId(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    public EventDto updateEventFromInitiator(@PathVariable Integer userId,
                                             @PathVariable Integer eventId,
                                             @RequestBody @Valid CreateEventDto createEventDto) {
        log.info("Update event with id = {} from userId = {}", eventId, userId);
        return eventService.updateEventFromInitiator(userId, eventId, createEventDto);
    }
}
