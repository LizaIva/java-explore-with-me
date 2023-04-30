package ru.practicum.explorewithme.ewm.service.request;

import ru.practicum.explorewithme.ewm.dto.request.InformationRequestDto;
import ru.practicum.explorewithme.ewm.dto.request.RequestDto;
import ru.practicum.explorewithme.ewm.model.event.Event;
import ru.practicum.explorewithme.ewm.model.request.EventRequestStatusUpdateResultDto;
import ru.practicum.explorewithme.ewm.model.request.Request;

import java.util.List;
import java.util.Set;

public interface RequestService {
    String DUPLICATE_EXCEPTION = "Request with userId = %s and eventId = %s already exist.";

    String VALIDATION_REQUEST_EXCEPTION = "Impossible to create a request for this event.";

    RequestDto put(Integer userId, Integer eventId);

    EventRequestStatusUpdateResultDto updateRequest(Integer userId, Integer eventId, InformationRequestDto informationRequestDto);

    List<RequestDto> getRequestsByUserId(Integer userId);

    RequestDto getRequestByUserIdAndRequestId(Integer userId, Integer requestId);

    List<RequestDto> getRequestByUserIdAndEventId(Integer userId, Integer eventId);

    void checkRequestAlreadyExist(Integer userId, Integer eventId);

    void checkUserIsInitiatorEvent(Integer eventId, Integer userId);

    void checkRequestStatus(Request request);
}
