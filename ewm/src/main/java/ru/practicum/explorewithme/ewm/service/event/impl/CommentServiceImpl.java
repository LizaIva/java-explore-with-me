package ru.practicum.explorewithme.ewm.service.event.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.ewm.dto.event.CommentDto;
import ru.practicum.explorewithme.ewm.dto.event.CreateCommentDto;
import ru.practicum.explorewithme.ewm.model.event.Comment;
import ru.practicum.explorewithme.ewm.model.event.State;
import ru.practicum.explorewithme.ewm.service.event.CommentService;
import ru.practicum.explorewithme.ewm.storage.event.CommentStorage;
import ru.practicum.explorewithme.ewm.storage.event.EventStorage;
import ru.practicum.explorewithme.ewm.storage.user.UserStorage;
import ru.practicum.explorewithme.ewm.utils.event.CommentMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final EventStorage eventStorage;
    private final UserStorage userStorage;
    private final CommentStorage commentStorage;
    private final CommentMapper commentMapper;

    @Override
    public CommentDto putComment(CreateCommentDto commentDto, Integer userId, Integer eventId) {
        userStorage.checkUser(userId);
        eventStorage.checkEvent(eventId);

        Comment createComment = commentMapper.mapToComment(commentDto, userId, eventId);
        Comment actualComment = commentStorage.put(createComment);
        log.info("Create comment with id {} from user with id {} to event with id {}", actualComment.getId(),
                userId, eventId);
        return commentMapper.mapToCommentDto(actualComment);
    }

    @Override
    public CommentDto updateState(Integer commentId, State state) {
        commentStorage.checkComment(commentId);

        Comment actualComment = commentStorage.getCommentById(commentId);
        if (state != null) {
            actualComment.setState(state);
        }

        Comment updatedComment = commentStorage.put(actualComment);
        return commentMapper.mapToCommentDto(updatedComment);
    }

    @Override
    public List<CommentDto> getAllCommentsByEventId(Integer eventId, Integer from, Integer size) {
        eventStorage.checkEvent(eventId);

        List<Comment> comments = commentStorage.getAllCommentByEventId(eventId, from, size);

        return commentMapper.mapToCommentsDto(comments);
    }
}
