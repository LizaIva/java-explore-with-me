package ru.practicum.explorewithme.ewm.repository.category;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.ewm.model.category.Category;

import java.awt.print.Pageable;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>,
        PagingAndSortingRepository<Category, Integer> {
}
