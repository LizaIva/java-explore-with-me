package ru.practicum.explorewithme.ewm.repository.compilation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.ewm.model.compilation.Compilation;


@Repository
public interface CompilationRepository extends JpaRepository<Compilation, Integer>,
        PagingAndSortingRepository<Compilation, Integer> {
    Page<Compilation> findAllByPinnedIs(Boolean pinned, Pageable pageable);
}
