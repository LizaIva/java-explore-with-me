package ru.practicum.explorewithme.ewm.utils.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.ewm.dto.event.CommentDto;
import ru.practicum.explorewithme.ewm.dto.event.CreateCommentDto;
import ru.practicum.explorewithme.ewm.model.event.Comment;
import ru.practicum.explorewithme.ewm.storage.event.EventStorage;
import ru.practicum.explorewithme.ewm.storage.user.UserStorage;

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

    public Comment mapToComment(CreateCommentDto commentDto) {
        return Comment.builder()
                .text(commentDto.getText())
                .user(userStorage.getById(commentDto.getUserId()))
                .event(eventStorage.getEventById(commentDto.getEventId()))
                .state(commentDto.getState())
                .build();
    }

    public List<CommentDto> mapToCommentsDto(List<Comment> comments) {
        return comments.stream()
                .map(this::mapToCommentDto)
                .collect(Collectors.toList());
    }
}
