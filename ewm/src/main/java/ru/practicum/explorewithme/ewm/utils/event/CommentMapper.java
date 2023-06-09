package ru.practicum.explorewithme.ewm.utils.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.ewm.dto.event.CommentDto;
import ru.practicum.explorewithme.ewm.dto.event.CreateCommentDto;
import ru.practicum.explorewithme.ewm.model.event.Comment;
import ru.practicum.explorewithme.ewm.model.event.State;
import ru.practicum.explorewithme.ewm.storage.event.EventStorage;
import ru.practicum.explorewithme.ewm.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final UserStorage userStorage;
    private final EventStorage eventStorage;

    public CommentDto mapToCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .state(comment.getState())
                .userId(comment.getUser().getId())
                .eventId(comment.getEvent().getId())
                .build();
    }

    public Comment mapToComment(CreateCommentDto commentDto, Integer userId, Integer eventId) {
        return Comment.builder()
                .text(commentDto.getText())
                .user(userStorage.getById(userId))
                .event(eventStorage.getEventById(eventId))
                .state(State.PENDING)
                .build();
    }

    public List<CommentDto> mapToCommentsDto(List<Comment> comments) {
        List<CommentDto> commentsDto = new ArrayList<>();

        if (comments == null || comments.isEmpty()) {
            return commentsDto;
        }
        return comments.stream()
                .map(this::mapToCommentDto)
                .collect(Collectors.toList());
    }
}
