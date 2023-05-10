package ru.practicum.explorewithme.ewm.service.event;

import ru.practicum.explorewithme.ewm.dto.event.CommentDto;
import ru.practicum.explorewithme.ewm.dto.event.CreateCommentDto;
import ru.practicum.explorewithme.ewm.model.event.State;

import java.util.List;

public interface CommentService {
    CommentDto putComment(CreateCommentDto commentDto, Integer userId, Integer eventId);

    CommentDto updateState(Integer commentId, State state);

    List<CommentDto> getAllCommentsByEventId(Integer eventId, Integer from, Integer size);
}
