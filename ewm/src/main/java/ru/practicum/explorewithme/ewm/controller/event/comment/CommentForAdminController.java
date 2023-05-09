package ru.practicum.explorewithme.ewm.controller.event.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.ewm.dto.event.CommentDto;
import ru.practicum.explorewithme.ewm.model.event.State;
import ru.practicum.explorewithme.ewm.service.event.CommentService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin/comments/{commentId}")
public class CommentForAdminController {
    private final CommentService commentService;

    @PatchMapping
    public CommentDto update(@PathVariable Integer commentId,
                             @RequestParam(name = "state") State state) {
        log.info("Update state fos comment with id {}", commentId);
        return commentService.updateState(commentId, state);
    }
}
