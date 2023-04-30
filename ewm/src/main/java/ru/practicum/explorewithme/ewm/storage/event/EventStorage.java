package ru.practicum.explorewithme.ewm.storage.event;

import ru.practicum.explorewithme.ewm.model.event.Event;
import ru.practicum.explorewithme.ewm.model.event.State;

import java.time.LocalDateTime;
import java.util.List;

public interface EventStorage {
    String EVENT_NOT_FOUND = "Event with id = %s not found.";

    String USER_NOT_INITIATOR_FOR_EVENT = "User with id = %s doesn't update event with id = %s";


    Event put(Event event);

    List<Event> findAllByInitiatorId(Integer initiatorId, Integer from, Integer size);

    Event findEventByInitiatorIdAndEventId(Integer initiatorId, Integer eventId);

    Event updateEvent(Integer initiatorId, Integer eventId);

    Event getEventById(Integer eventId);

    Event getEventByIdAndState(Integer eventId, State state);

    List<Event> findAllByInitiatorAndStatesAndCategories(List<Integer> initiatorsId,
                                                         List<State> states,
                                                         List<Integer> categories,
                                                         LocalDateTime rangeStart,
                                                         LocalDateTime rangeEnd,
                                                         Integer from, Integer size);

    List<Event> findAllByFilter(String text,
                                List<Integer> categories,
                                Boolean paid,
                                LocalDateTime rangeStart,
                                LocalDateTime rangeEnd,
                                Boolean onlyAvailable,
                                String sort,
                                Integer from, Integer size);

    void checkUserOwnerByEventId(Integer eventId, Integer userId);

    Boolean checkUserInitiatorForEvent(Integer eventId, Integer userId);

    void checkEvent(Integer id);

    List<Event> findAllByIdIn(List<Integer> events);
}
