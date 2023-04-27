package ru.practicum.explorewithme.ewm.controller.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.ewm.dto.request.RequestDto;
import ru.practicum.explorewithme.ewm.service.request.RequestService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users/{userId}/requests")
public class RequestForUserController {
    private final RequestService requestService;

    @PostMapping
    public RequestDto create(@PathVariable Integer userId,
                             @RequestParam(name = "eventId") Integer eventId) {
        log.info("Create request from user with id = {} and event with id = {}", userId, eventId);
        return requestService.put(userId, eventId);
    }

    @GetMapping
    public List<RequestDto> getByUserId(@PathVariable Integer userId) {
        log.info("Get all requests with userId = {}.", userId);
        return requestService.getRequestsByUserId(userId);
    }

    @PatchMapping("/{requestId}/cancel")
    public RequestDto cancelRequest(@PathVariable Integer userId,
                                    @PathVariable Integer requestId) {
        log.info("Cancel request with id = {} from user with id = {}", requestId, userId);
        return requestService.getRequestByUserIdAndRequestId(userId, requestId);
    }
}
