package ru.practicum.explorewithme.ewm.storage.event.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.ewm.exception.UnknownDataException;
import ru.practicum.explorewithme.ewm.model.event.Comment;
import ru.practicum.explorewithme.ewm.model.event.State;
import ru.practicum.explorewithme.ewm.repository.event.CommentRepository;
import ru.practicum.explorewithme.ewm.storage.event.CommentStorage;

import java.util.List;

@Component("commentDbStorageImpl")
@RequiredArgsConstructor
public class CommentDbStorageImpl implements CommentStorage {
    private final CommentRepository commentRepository;

    @Override
    public Comment put(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }

    @Override
    public Comment getCommentById(Integer commentId) {
        return commentRepository.getReferenceById(commentId);
    }

    @Override
    public List<Comment> getAllCommentByEventId(Integer eventId, Integer from, Integer size) {
        return commentRepository.findAllByEventIdAndState(eventId, State.PUBLISHED,
                PageRequest.of(from, size)).getContent();
    }

    @Override
    public void checkComment(Integer id) {
        if (!commentRepository.existsById(id)) {
            throw new UnknownDataException(String.format(COMMENT_NOT_FOUND, id));
        }
    }
}
