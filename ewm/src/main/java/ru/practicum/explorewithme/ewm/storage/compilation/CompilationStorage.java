package ru.practicum.explorewithme.ewm.storage.compilation;

import ru.practicum.explorewithme.ewm.model.compilation.Compilation;

import java.util.List;

public interface CompilationStorage {
    String COMPILATION_NOT_FOUND = "Compilation with id = %s not found.";
    Compilation put(Compilation compilation);

    void delete(Integer compilationId);

    Compilation update(Compilation compilation);

    Compilation getById(Integer id);

    List<Compilation> findAllByPinned(Boolean pinned, Integer from, Integer size);

    void checkCompilation(int id);
}
