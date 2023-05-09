package ru.practicum.explorewithme.ewm.storage.event.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.ewm.exception.CheckInitiatorException;
import ru.practicum.explorewithme.ewm.exception.UnknownDataException;
import ru.practicum.explorewithme.ewm.model.event.Event;
import ru.practicum.explorewithme.ewm.model.event.State;
import ru.practicum.explorewithme.ewm.repository.event.EventRepository;
import ru.practicum.explorewithme.ewm.storage.event.EventStorage;

import java.time.LocalDateTime;
import java.util.List;

@Component("eventDbStorageImpl")
@RequiredArgsConstructor
public class EventDbStorageImpl implements EventStorage {
    private final EventRepository eventRepository;
    private static final Sort PAGIN_SORT_EVENT_DATE = Sort.by(List.of(Sort.Order.desc("eventDate")));
    private static final Sort PAGIN_SORT_VIEWS = Sort.by(List.of(Sort.Order.desc("views")));

    @Override
    public Event put(Event event) {
        return eventRepository.saveAndFlush(event);
    }

    @Override
    public List<Event> findAllByInitiatorId(Integer initiatorId, Integer from, Integer size) {
        return eventRepository.findAllByInitiatorId(initiatorId, PageRequest.of(from, size)).getContent();
    }

    @Override
    public Event findEventByInitiatorIdAndEventId(Integer initiatorId, Integer eventId) {
        return eventRepository.findEventByInitiatorIdAndId(initiatorId, eventId);
    }

    @Override
    public Event updateEvent(Integer initiatorId, Integer eventId) {
        return eventRepository.findEventByInitiatorIdAndId(initiatorId, eventId);
    }

    @Override
    public Event getEventById(Integer eventId) {
        return eventRepository.getReferenceById(eventId);
    }

    @Override
    public Event getEventByIdAndState(Integer eventId, State state) {
        return eventRepository.findEventByIdAndState(eventId, state);
    }

    @Override
    public List<Event> findAllByInitiatorAndStatesAndCategories(List<Integer> initiatorsId,
                                                                List<State> states,
                                                                List<Integer> categories,
                                                                LocalDateTime rangeStart,
                                                                LocalDateTime rangeEnd,
                                                                Integer from, Integer size) {
        return eventRepository.findAllByInitiatorAndStatesAndCategories(
                initiatorsId,
                initiatorsId == null ? 1 : 0,
                states,
                states == null ? 1 : 0,
                categories,
                categories == null ? 1 : 0,
                rangeStart,
                rangeEnd,
                PageRequest.of(from, size)
        ).getContent();
    }

    @Override
    public List<Event> findAllByFilter(String text,
                                       List<Integer> categories,
                                       Boolean paid,
                                       LocalDateTime rangeStart,
                                       LocalDateTime rangeEnd,
                                       Boolean onlyAvailable,
                                       String sort,
                                       Integer from, Integer size) {
        Sort sortFilter = null;
        if ("EVENT_DATE".equals(sort)) {
            sortFilter = PAGIN_SORT_EVENT_DATE;
        } else {
            sortFilter = PAGIN_SORT_VIEWS;
        }

        return eventRepository.findAllByFilterOnlyAvailable(
                text == null ? null : text.toLowerCase(),
                text == null ? 1 : 0,
                categories,
                categories == null ? 1 : 0,
                paid, rangeStart,
                rangeEnd, onlyAvailable, PageRequest.of(from, size, sortFilter)).getContent();
    }

    @Override
    public void checkUserOwnerByEventId(Integer eventId, Integer userId) {
        if (!checkUserInitiatorForEvent(eventId, userId)) {
            throw new CheckInitiatorException((String.format(USER_NOT_INITIATOR_FOR_EVENT, userId, eventId)));
        }
    }

    @Override
    public Boolean checkUserInitiatorForEvent(Integer eventId, Integer userId) {
        return eventRepository.isUserInitiatorForEvent(eventId, userId);
    }

    @Override
    public void checkEvent(Integer id) {
        if (!eventRepository.existsById(id)) {
            throw new UnknownDataException(String.format(EVENT_NOT_FOUND, id));
        }
    }

    @Override
    public List<Event> findAllByIdIn(List<Integer> events) {
        return eventRepository.findAllByIdIn(events);
    }
}
