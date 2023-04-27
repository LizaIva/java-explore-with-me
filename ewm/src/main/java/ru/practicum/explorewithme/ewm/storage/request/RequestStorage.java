package ru.practicum.explorewithme.ewm.storage.request;

import ru.practicum.explorewithme.ewm.model.request.Request;

import java.util.List;
import java.util.Set;

public interface RequestStorage {
    String REQUEST_NOT_FOUND = "Request with id = %s not found.";
    Request put(Request request);

    List<Request> getRequestsByUserId(Integer userId);

    Request getRequestByUserIdAndRequestId(Integer userId, Integer requestId);

    List<Request> getRequestByUserIdAndEventId(Integer userId, Integer eventId);

    List<Request> getRequestByIds(Set<Integer> ids);

    Boolean checkRequest(Integer userId, Integer eventId);

    void checkRequestExist(Integer id);
}
