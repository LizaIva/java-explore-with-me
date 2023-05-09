package ru.practicum.explorewithme.ewm.controller.event.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.ewm.dto.event.CommentDto;
import ru.practicum.explorewithme.ewm.dto.event.CreateCommentDto;
import ru.practicum.explorewithme.ewm.service.event.CommentService;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users/{userId}/comments/{eventId}")
public class CommentForUserController {
    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@RequestBody @Valid CreateCommentDto commentDto) {
        log.info("Create comment");
        return commentService.putComment(commentDto);
    }
}
