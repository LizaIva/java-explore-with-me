package ru.practicum.explorewithme.ewm.storage.request.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.ewm.exception.CheckValidationRequestException;
import ru.practicum.explorewithme.ewm.exception.UnknownDataException;
import ru.practicum.explorewithme.ewm.model.event.State;
import ru.practicum.explorewithme.ewm.model.request.Request;
import ru.practicum.explorewithme.ewm.repository.request.RequestRepository;
import ru.practicum.explorewithme.ewm.storage.request.RequestStorage;

import java.util.List;
import java.util.Set;

@Component("requestDbStorageImpl")
@RequiredArgsConstructor
public class RequestDbStorage implements RequestStorage {
    private final RequestRepository requestRepository;

    @Override
    public Request put(Request request) {
        return requestRepository.saveAndFlush(request);
    }

    @Override
    public List<Request> getRequestsByUserId(Integer userId) {
        return requestRepository.getRequestsByRequesterId(userId);
    }

    @Override
    public Request getRequestByUserIdAndRequestId(Integer userId, Integer requestId) {
        return requestRepository.getRequestByRequesterIdAndId(userId, requestId);
    }

    @Override
    public List<Request> getRequestByUserIdAndEventId(Integer userId, Integer eventId) {
        return requestRepository.getRequestsByRequesterIdAndEventId(userId, eventId);
    }

    @Override
    public List<Request> getRequestByIds(Set<Integer> ids) {
        return requestRepository.getRequestsByIdIsIn(ids);
    }


    @Override
    public Boolean checkRequest(Integer userId, Integer eventId) {
        return requestRepository.checkRequest(userId, eventId);
    }

    @Override
    public void checkRequestExist(Integer id) {
        if (!requestRepository.existsById(id)) {
            throw new UnknownDataException(String.format(REQUEST_NOT_FOUND, id));
        }
    }
}
