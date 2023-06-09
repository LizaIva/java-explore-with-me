package ru.practicum.explorewithme.ewm.service.request.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.ewm.dto.request.InformationRequestDto;
import ru.practicum.explorewithme.ewm.dto.request.RequestDto;
import ru.practicum.explorewithme.ewm.exception.CheckDuplicateRequestException;
import ru.practicum.explorewithme.ewm.exception.CheckValidationRequestException;
import ru.practicum.explorewithme.ewm.model.event.Event;
import ru.practicum.explorewithme.ewm.model.event.State;
import ru.practicum.explorewithme.ewm.model.request.EventRequestStatusUpdateResultDto;
import ru.practicum.explorewithme.ewm.model.request.Request;
import ru.practicum.explorewithme.ewm.model.request.Status;
import ru.practicum.explorewithme.ewm.service.request.RequestService;
import ru.practicum.explorewithme.ewm.storage.event.EventStorage;
import ru.practicum.explorewithme.ewm.storage.request.RequestStorage;
import ru.practicum.explorewithme.ewm.storage.user.UserStorage;
import ru.practicum.explorewithme.ewm.utils.request.RequestMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.practicum.explorewithme.ewm.model.request.Status.CANCELED;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestStorage requestStorage;
    private final RequestMapper requestMapper;
    private final UserStorage userStorage;
    private final EventStorage eventStorage;

    @Override
    public RequestDto put(Integer userId, Integer eventId) {
        userStorage.checkUser(userId);
        eventStorage.checkEvent(eventId);

        checkRequestAlreadyExist(userId, eventId);
        checkUserIsInitiatorEvent(eventId, userId);

        Event event = eventStorage.getEventById(eventId);
        if (!State.PUBLISHED.equals(event.getState())
                || event.getConfirmedRequests() + 1 > event.getParticipantLimit()) {
            throw new CheckValidationRequestException(VALIDATION_REQUEST_EXCEPTION);
        }

        RequestDto createdRequestDto = RequestDto.builder()
                .requester(userId)
                .event(eventId)
                .created(LocalDateTime.now())
                .build();
        if (event.getRequestModeration()) {
            createdRequestDto.setStatus(Status.PENDING);
        } else {
            createdRequestDto.setStatus(Status.CONFIRMED);
            event.setConfirmedRequests(event.getConfirmedRequests() + 1);
            log.info("Update confirmedRequests event with id = {}", event.getId());
            eventStorage.put(event);
        }

        Request request = requestMapper.mapToRequest(createdRequestDto);

        log.info("Put request with userId = {} and eventId = {}", userId, eventId);
        Request actualRequest = requestStorage.put(request);
        return requestMapper.mapToRequestDto(actualRequest);
    }

    @Override
    public EventRequestStatusUpdateResultDto updateRequest(Integer userId, Integer eventId, InformationRequestDto informationRequestDto) {

        userStorage.checkUser(userId);
        eventStorage.checkEvent(eventId);
        for (Integer id : informationRequestDto.getRequestIds()) {
            requestStorage.checkRequestExist(id);
        }

        List<Request> requests = requestStorage.getRequestByIds(informationRequestDto.getRequestIds());
        for (Request request : requests) {
            checkRequestStatus(request);
        }

        Event event = eventStorage.getEventById(eventId);
        EventRequestStatusUpdateResultDto result = new EventRequestStatusUpdateResultDto();
        if (!event.getRequestModeration() || event.getParticipantLimit() == null || event.getParticipantLimit() == 0) {
            result.setConfirmedRequests(requestMapper.mapToRequestsDto(requests));
        } else {
            List<Request> confirmed = new ArrayList<>();
            List<Request> rejected = new ArrayList<>();

            for (Request request : requests) {
                if (event.getConfirmedRequests() + 1 > event.getParticipantLimit()) {
                    throw new CheckValidationRequestException(VALIDATION_REQUEST_EXCEPTION);
                }
                if (Status.CONFIRMED.equals(informationRequestDto.getStatus())) {
                    request.setStatus(Status.CONFIRMED);
                    requestStorage.put(request);
                    event.setConfirmedRequests(event.getConfirmedRequests() + 1);
                    eventStorage.put(event);

                    confirmed.add(request);
                } else {

                    request.setStatus(Status.REJECTED);
                    requestStorage.put(request);

                    rejected.add(request);
                }
            }

            result.setConfirmedRequests(requestMapper.mapToRequestsDto(confirmed));
            result.setRejectedRequests(requestMapper.mapToRequestsDto(rejected));
        }

        return result;
    }

    @Override
    public List<RequestDto> getRequestsByUserId(Integer userId) {
        userStorage.checkUser(userId);

        List<Request> currentRequests = requestStorage.getRequestsByUserId(userId);
        return requestMapper.mapToRequestsDto(currentRequests);
    }

    @Override
    public RequestDto getRequestByUserIdAndRequestId(Integer userId, Integer requestId) {
        userStorage.checkUser(userId);
        requestStorage.checkRequestExist(requestId);

        Request currentRequest = requestStorage.getRequestByUserIdAndRequestId(userId, requestId);
        currentRequest.setStatus(CANCELED);
        requestStorage.put(currentRequest);

        return requestMapper.mapToRequestDto(currentRequest);
    }

    @Override
    public List<RequestDto> getRequestByUserIdAndEventId(Integer userId, Integer eventId) {
        userStorage.checkUser(userId);
        eventStorage.checkEvent(eventId);

        List<Request> currentRequests = requestStorage.getRequestByUserIdAndEventId(userId, eventId);
        return requestMapper.mapToRequestsDto(currentRequests);
    }


    @Override
    public void checkRequestAlreadyExist(Integer userId, Integer eventId) {
        if (requestStorage.checkRequest(userId, eventId)) {
            throw new CheckDuplicateRequestException(String.format(DUPLICATE_EXCEPTION, userId, eventId));
        }
    }

    @Override
    public void checkUserIsInitiatorEvent(Integer eventId, Integer userId) {
        if (eventStorage.checkUserInitiatorForEvent(eventId, userId)) {
            throw new CheckDuplicateRequestException(String.format(DUPLICATE_EXCEPTION, userId, eventId));
        }
    }

    @Override
    public void checkRequestStatus(Request request) {
        if (!Status.PENDING.equals(request.getStatus())) {
            throw new CheckValidationRequestException(VALIDATION_REQUEST_EXCEPTION);
        }
    }
}
