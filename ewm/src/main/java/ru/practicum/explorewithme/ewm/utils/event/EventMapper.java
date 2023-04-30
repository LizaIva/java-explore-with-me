package ru.practicum.explorewithme.ewm.utils.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.ewm.dto.event.CreateEventDto;
import ru.practicum.explorewithme.ewm.dto.event.EventDto;
import ru.practicum.explorewithme.ewm.dto.event.LocationDto;
import ru.practicum.explorewithme.ewm.model.event.Event;
import ru.practicum.explorewithme.ewm.storage.categoty.CategoryStorage;
import ru.practicum.explorewithme.ewm.storage.user.UserStorage;
import ru.practicum.explorewithme.ewm.utils.category.CategoryMapper;
import ru.practicum.explorewithme.ewm.utils.user.UserMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.explorewithme.ewm.model.event.State.PENDING;

@Component
@RequiredArgsConstructor
public class EventMapper {
    private final CategoryStorage categoryStorage;
    private final UserStorage userStorage;
    private final CategoryMapper categoryMapper;
    private final UserMapper userMapper;

    public Event mapToEvent(CreateEventDto createEventDto, Integer userId) {
        return Event.builder()
                .state(PENDING)
                .confirmedRequests(0)
                .views(0)
                .participantLimit(createEventDto.getParticipantLimit())
                .createdOn(LocalDateTime.now())
                .publishedOn(LocalDateTime.now())
                .annotation(createEventDto.getAnnotation())
                .description(createEventDto.getDescription())
                .category(categoryStorage.getById(createEventDto.getCategory()))
                .eventDate(createEventDto.getEventDate())
                .initiator(userStorage.getById(userId))
                .paid(createEventDto.getPaid())
                .title(createEventDto.getTitle())
                .requestModeration(createEventDto.getRequestModeration())
                .locationLat(createEventDto.getLocation().getLat())
                .locationLon(createEventDto.getLocation().getLon())
                .build();
    }

    public EventDto mapToEventDto(Event event) {
        return EventDto.builder()
                .annotation(event.getAnnotation())
                .category(categoryMapper.mapToCategoryDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .createdOn(event.getCreatedOn())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .id(event.getId())
                .initiator(userMapper.mapToUserDto(event.getInitiator()))
                .location(mapToLocationDto(event.getLocationLat(), event.getLocationLon()))
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn())
                .requestModeration(event.getRequestModeration())
                .state(event.getState())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }

    public List<EventDto> mapToEventsDto(List<Event> events) {
        return events.stream()
                .map(this::mapToEventDto)
                .collect(Collectors.toList());
    }

    public LocationDto mapToLocationDto(Double lat, Double lon) {
        return LocationDto.builder()
                .lat(lat)
                .lon(lon)
                .build();
    }
}
