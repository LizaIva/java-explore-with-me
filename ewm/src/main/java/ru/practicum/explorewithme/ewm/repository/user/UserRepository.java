package ru.practicum.explorewithme.ewm.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.ewm.model.user.User;

import java.awt.print.Pageable;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>,
        PagingAndSortingRepository<User, Integer> {
    Page<User> findAll(Pageable pageable);
}