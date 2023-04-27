package ru.practicum.explorewithme.ewm.repository.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.ewm.model.request.Request;

import java.util.List;
import java.util.Set;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    @Query("select count(*) > 0 " +
            "   from requests r " +
            "   where r.requester.id = :userId and  r.event.id = :eventId ")
    Boolean checkRequest(@Param("userId") int userId, @Param("eventId") int eventId);

    List<Request> getRequestsByRequesterId(Integer requesterId);

    Request getRequestByRequesterIdAndId(Integer requesterId, Integer id);

    List<Request> getRequestsByRequesterIdAndEventId(Integer requesterId, Integer eventId);

    List<Request> getRequestsByIdIsIn(Set<Integer> ids);
}

