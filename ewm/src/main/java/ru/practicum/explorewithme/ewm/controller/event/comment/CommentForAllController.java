package ru.practicum.explorewithme.ewm.controller.event.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.ewm.client.StatsClient;
import ru.practicum.explorewithme.ewm.dto.event.CommentDto;
import ru.practicum.explorewithme.ewm.service.event.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/comments/{eventId}")
public class CommentForAllController {
    private final CommentService commentService;
    private final StatsClient statsClient;

    @GetMapping
    public List<CommentDto> getAllCommentsByEventId(@PathVariable Integer eventId,
                                                    @RequestParam(name = "from", required = false, defaultValue = "0")
                                                    Integer from,
                                                    @RequestParam(name = "size", required = false, defaultValue = "10")
                                                    Integer size,
                                                    HttpServletRequest request) {
        log.info("Get all comments by eventId {}", eventId);
        statsClient.hitCall(request);
        return commentService.getAllCommentsByEventId(eventId, from, size);
    }
}
