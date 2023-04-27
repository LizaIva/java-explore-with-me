package ru.practicum.explorewithme.ewm.service.event;

import ru.practicum.explorewithme.ewm.dto.event.CreateEventDto;
import ru.practicum.explorewithme.ewm.dto.event.EventDto;
import ru.practicum.explorewithme.ewm.model.event.State;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    String TIME_EXCEPTION ="Not possible to create an event with a time %s";
    String STATE_EXCEPTION ="Not possible to update an event with state PUBLISHED";
    EventDto put(CreateEventDto createEventDto, Integer userId);

    List<EventDto> getAllByInitiatorId(Integer initiatorId, Integer from, Integer size);

    EventDto getEventByInitiatorIdAndEventId(Integer initiatorId, Integer eventId);

    EventDto updateEventFromInitiator(Integer initiatorId, Integer eventId, CreateEventDto createEventDto);

    EventDto updateEventFromAdmin(Integer eventId, CreateEventDto createEventDto);

    List<EventDto> getEventsByInitiatorsStatesCategories(List<Integer> initiatorsId,
                                                         List<String> states,
                                                         List<Integer> categories,
                                                         String rangeStart,
                                                         String rangeEnd,
                                                         Integer from, Integer size);

    List<EventDto> getEventsByFilter(String text,
                                     List<Integer> categories,
                                     Boolean paid,
                                     String rangeStart,
                                     String rangeEnd,
                                     Boolean onlyAvailable,
                                     String sort,
                                     Integer from, Integer size);

    EventDto getEventById(Integer eventId);

    EventDto getEventByIdAndState(Integer eventId);

    void checkEventTime(LocalDateTime time);

    void checkEventTimeForAdmin(LocalDateTime time);

    void checkState(State state);
}
