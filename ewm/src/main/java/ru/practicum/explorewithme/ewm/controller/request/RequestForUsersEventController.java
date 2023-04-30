package ru.practicum.explorewithme.ewm.controller.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.ewm.dto.request.InformationRequestDto;
import ru.practicum.explorewithme.ewm.dto.request.RequestDto;
import ru.practicum.explorewithme.ewm.dto.user.CreateUserDto;
import ru.practicum.explorewithme.ewm.model.request.EventRequestStatusUpdateResultDto;
import ru.practicum.explorewithme.ewm.service.request.RequestService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users/{userId}/events/{eventId}/requests")
public class RequestForUsersEventController {
    private final RequestService requestService;

    @GetMapping
    public List<RequestDto> getAllRequestByUserIdAndEventId(@PathVariable Integer userId,
                                                            @PathVariable Integer eventId) {
        log.info("Get all requests by userId = {} and eventId = {}.", userId, eventId);
        return requestService.getRequestByUserIdAndEventId(userId, eventId);
    }

    @PatchMapping
    public EventRequestStatusUpdateResultDto updateRequest(@PathVariable Integer userId,
                                                           @PathVariable Integer eventId,
                                                           @RequestBody InformationRequestDto requestsDto
                                                           ) {
        log.info("Update events from userId = {}", userId);
        return requestService.updateRequest(userId, eventId, requestsDto);
    }
}
