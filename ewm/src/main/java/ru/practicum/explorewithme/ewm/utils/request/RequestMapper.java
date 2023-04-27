package ru.practicum.explorewithme.ewm.utils.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.ewm.dto.request.RequestDto;
import ru.practicum.explorewithme.ewm.model.request.Request;
import ru.practicum.explorewithme.ewm.storage.event.EventStorage;
import ru.practicum.explorewithme.ewm.storage.user.UserStorage;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestMapper {
    private final UserStorage userStorage;
    private final EventStorage eventStorage;

    public RequestDto mapToRequestDto(Request request) {
        return RequestDto.builder()
                .id(request.getId())
                .requester(request.getRequester().getId())
                .created(request.getCreated())
                .status(request.getStatus())
                .event(request.getEvent().getId())
                .build();
    }

    public Request mapToRequest(RequestDto requestDto) {
        return Request.builder()
                .requester(userStorage.getById(requestDto.getRequester()))
                .created(requestDto.getCreated())
                .status(requestDto.getStatus())
                .event(eventStorage.getEventById(requestDto.getEvent()))
                .build();
    }

    public List<RequestDto> mapToRequestsDto(List<Request> requests) {
        return requests.stream()
                .map(this::mapToRequestDto)
                .collect(Collectors.toList());
    }
}
