package ru.practicum.explorewithme.ewm.repository.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.ewm.model.event.Comment;
import ru.practicum.explorewithme.ewm.model.event.State;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>,
        PagingAndSortingRepository<Comment, Integer> {

    Page<Comment> findAllByEventIdAndState(Integer eventId, State state, Pageable pageable);
}
