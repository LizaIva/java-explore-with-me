package ru.practicum.explorewithme.ewm.repository.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.ewm.model.event.Event;
import ru.practicum.explorewithme.ewm.model.event.State;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>,
        PagingAndSortingRepository<Event, Integer> {
    List<Event> findAllByIdIn(List<Integer> ids);

    Page<Event> findAllByInitiatorId(Integer initiatorId, Pageable pageable);

    Event findEventByInitiatorIdAndId(Integer initiatorId, Integer eventId);

    @Query("select count(*) > 0 " +
            "   from events e " +
            "   where e.id = :eventId and  e.initiator.id = :userId")
    Boolean isUserInitiatorForEvent(@Param("eventId") int eventId, @Param("userId") int userId);

    @Query("select e from events e " +
            "where (:skipInitiatorSearch = 1 or e.initiator.id IN (:initiatorsId) )" +
            "and (:skipStatesSearch = 1 or e.state IN (:states)) " +
            "and (:skipCategoriesSearch = 1 or e.category.id IN (:categories)) " +
            "and (cast(:rangeStart as date) is null or e.eventDate > :rangeStart) " +
            "and (cast(:rangeEnd as date) is null or e.eventDate < :rangeEnd) ")
    Page<Event> findAllByInitiatorAndStatesAndCategories(
            @Param("initiatorsId") List<Integer> initiatorsId,
            @Param("skipInitiatorSearch") Integer skipInitiatorSearch,
            @Param("states") List<State> states,
            @Param("skipStatesSearch") Integer skipStatesSearch,
            @Param("categories") List<Integer> categories,
            @Param("skipCategoriesSearch") Integer skipCategoriesSearch,
            @Param("rangeStart") LocalDateTime rangeStart,
            @Param("rangeEnd") LocalDateTime rangeEnd,
            Pageable pageable
    );

    @Query("select e from events e " +
            "where (:skipTextSearch = 1 or (lower(e.annotation) like concat('%', :text, '%') or lower(e.description) like concat('%', :text, '%')))" +
            "and (:skipCategoriesSearch = 1 or e.category.id IN(:categories)) " +
            "and (cast(:paid as boolean ) is null or e.paid = :paid) " +
            "and e.eventDate > :rangeStart " +
            "and e.eventDate < :rangeEnd " +
            "and (cast(:onlyAvailable as boolean ) is null or e.confirmedRequests <= e.participantLimit+1 )")
    Page<Event> findAllByFilterOnlyAvailable(@Param("text") String text,
                                             @Param("skipTextSearch") Integer skipTextSearch,
                                             @Param("categories") List<Integer> categories,
                                             @Param("skipCategoriesSearch") Integer skipCategoriesSearch,
                                             @Param("paid") Boolean paid,
                                             @Param("rangeStart") LocalDateTime rangeStart,
                                             @Param("rangeEnd") LocalDateTime rangeEnd,
                                             @Param("onlyAvailable") Boolean onlyAvailable,
                                             Pageable pageable);

    Event findEventByIdAndState(int eventId, State state);
}
