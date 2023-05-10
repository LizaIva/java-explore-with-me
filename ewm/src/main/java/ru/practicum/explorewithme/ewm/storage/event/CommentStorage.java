package ru.practicum.explorewithme.ewm.storage.event;

import ru.practicum.explorewithme.ewm.model.event.Comment;

import java.util.List;

public interface CommentStorage {
    String COMMENT_NOT_FOUND = "Comment with id = %s not found.";

    Comment put(Comment comment);

    Comment getCommentById(Integer commentId);

    List<Comment> getAllCommentByEventId(Integer eventId, Integer from, Integer size);

    void checkComment(Integer id);
}
