package ru.practicum.explorewithme.ewm.repository.user;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.ewm.model.user.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>,
        PagingAndSortingRepository<User, Integer> {

    List<User> findAllByIdIn(@Param("ids") List<Integer> ids, Pageable pageable);
}
