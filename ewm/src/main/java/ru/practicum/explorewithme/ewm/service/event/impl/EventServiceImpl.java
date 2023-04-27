package ru.practicum.explorewithme.ewm.service.event.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.ewm.dto.event.CreateEventDto;
import ru.practicum.explorewithme.ewm.dto.event.EventDto;
import ru.practicum.explorewithme.ewm.exception.StateValidationException;
import ru.practicum.explorewithme.ewm.exception.TimeValidationException;
import ru.practicum.explorewithme.ewm.model.event.Event;
import ru.practicum.explorewithme.ewm.model.event.State;
import ru.practicum.explorewithme.ewm.service.event.EventService;
import ru.practicum.explorewithme.ewm.storage.categoty.CategoryStorage;
import ru.practicum.explorewithme.ewm.storage.event.EventStorage;
import ru.practicum.explorewithme.ewm.storage.user.UserStorage;
import ru.practicum.explorewithme.ewm.utils.event.EventMapper;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventStorage eventStorage;
    private final EventMapper eventMapper;
    private final UserStorage userStorage;
    private final CategoryStorage categoryStorage;


    private static final DateTimeFormatter START_END_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Override
    public EventDto put(CreateEventDto createEventDto, Integer userId) {
        userStorage.checkUser(userId);
        checkEventTime(createEventDto.getEventDate());

        Event createEvent = eventMapper.mapToEvent(createEventDto, userId);
        Event actualEvent = eventStorage.put(createEvent);
        log.info("Create event with id {} from user with id {}", actualEvent.getId(), userId);
        return eventMapper.mapToEventDto(actualEvent);
    }

    @Override
    public List<EventDto> getAllByInitiatorId(Integer initiatorId, Integer from, Integer size) {
        userStorage.checkUser(initiatorId);
        List<Event> events = eventStorage.findAllByInitiatorId(initiatorId, from, size);
        return eventMapper.mapToEventsDto(events);
    }

    @Override
    public EventDto getEventByInitiatorIdAndEventId(Integer initiatorId, Integer eventId) {
        userStorage.checkUser(initiatorId);
        eventStorage.checkEvent(eventId);
        return eventMapper.mapToEventDto(eventStorage.findEventByInitiatorIdAndEventId(initiatorId, eventId));
    }

    @Override
    public EventDto updateEventFromInitiator(Integer initiatorId, Integer eventId, CreateEventDto createEventDto) {
        userStorage.checkUser(initiatorId);
        eventStorage.checkEvent(eventId);
        eventStorage.checkUserOwnerByEventId(eventId, initiatorId);

        checkEventTime(createEventDto.getEventDate());

        Event currentEvent = eventStorage.getEventById(eventId);
        checkState(currentEvent.getState());

        return changeEvent(createEventDto, currentEvent);
    }

    @Override
    public EventDto updateEventFromAdmin(Integer eventId, CreateEventDto createEventDto) {
        eventStorage.checkEvent(eventId);

        checkEventTimeForAdmin(createEventDto.getEventDate());

        Event currentEvent = eventStorage.getEventById(eventId);
        checkState(currentEvent.getState());

        if ("PUBLISH_EVENT".equals(createEventDto.getStateAction())) {
            currentEvent.setState(State.PUBLISHED);
        } else {
            currentEvent.setState(State.CANCELED);
        }

        return changeEvent(createEventDto, currentEvent);
    }

    private EventDto changeEvent(CreateEventDto createEventDto, Event currentEvent) {
        currentEvent.setAnnotation(createEventDto.getAnnotation());
        currentEvent.setCategory(categoryStorage.getById(createEventDto.getCategory()));
        currentEvent.setDescription(createEventDto.getDescription());
        currentEvent.setEventDate(createEventDto.getEventDate());
        currentEvent.setLocation(eventMapper.mapToLocation(createEventDto.getLocation()));
        currentEvent.setPaid(currentEvent.getPaid());
        currentEvent.setParticipantLimit(createEventDto.getParticipantLimit());
        currentEvent.setRequestModeration(createEventDto.getRequestModeration());
        currentEvent.setTitle(createEventDto.getTitle());

        Event actualEvent = eventStorage.put(currentEvent);
        return eventMapper.mapToEventDto(actualEvent);
    }

    @Override
    public List<EventDto> getEventsByInitiatorsStatesCategories(List<Integer> initiatorsId,
                                                                List<String> states,
                                                                List<Integer> categories,
                                                                String rangeStart,
                                                                String rangeEnd,
                                                                Integer from, Integer size) {
        for (Integer userId : initiatorsId) {
            userStorage.checkUser(userId);
        }

        for (Integer categoryId : categories) {
            categoryStorage.checkCategory(categoryId);
        }

        LocalDateTime decodedStart = decodeDateTime(rangeStart);
        LocalDateTime decodedEnd = decodeDateTime(rangeEnd);

        List<Event> actualEvent = eventStorage.findAllByInitiatorAndStatesAndCategories(initiatorsId, states,
                categories, decodedStart, decodedEnd, from, size);

        return eventMapper.mapToEventsDto(actualEvent);
    }

    @Override
    public List<EventDto> getEventsByFilter(String text,
                                            List<Integer> categories,
                                            Boolean paid,
                                            String rangeStart,
                                            String rangeEnd,
                                            Boolean onlyAvailable,
                                            String sort,
                                            Integer from, Integer size) {
        for (Integer categoryId : categories) {
            categoryStorage.checkCategory(categoryId);
        }

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusYears(10);
        if (!rangeStart.isEmpty()) {
            start = decodeDateTime(rangeStart);
        }

        if (!rangeEnd.isEmpty()) {
            end = decodeDateTime(rangeEnd);
        }

        List<Event> events = eventStorage.findAllByFilter(text, categories, paid, start, end, onlyAvailable, sort, from, size);
        return eventMapper.mapToEventsDto(events);
    }

    @Override
    public EventDto getEventById(Integer eventId) {
        eventStorage.checkEvent(eventId);
        return eventMapper.mapToEventDto(eventStorage.getEventById(eventId));
    }

    @Override
    public EventDto getEventByIdAndState(Integer eventId) {
        eventStorage.checkEvent(eventId);

        Event actualEvent = eventStorage.getEventByIdAndState(eventId, State.PUBLISHED);
        return eventMapper.mapToEventDto(actualEvent);
    }

    @Override
    public void checkEventTime(LocalDateTime time) {
        if (time.isBefore(LocalDateTime.now().plusHours(2))) {
            throw new TimeValidationException(String.format(TIME_EXCEPTION, time));
        }
    }

    @Override
    public void checkEventTimeForAdmin(LocalDateTime time) {
        if (time.isBefore(LocalDateTime.now().plusHours(1))) {
            throw new TimeValidationException(String.format(TIME_EXCEPTION, time));
        }
    }

    @Override
    public void checkState(State state) {
        if (State.PUBLISHED.equals(state)) {
            throw new StateValidationException(String.format(STATE_EXCEPTION));
        }
    }

    private static LocalDateTime decodeDateTime(String dateTime) {
        return LocalDateTime.parse(URLDecoder.decode(dateTime, StandardCharsets.UTF_8), START_END_DATE_FORMATTER);
    }
}

