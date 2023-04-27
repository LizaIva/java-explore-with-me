package ru.practicum.explorewithme.ewm.storage.compilation.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.ewm.exception.UnknownDataException;
import ru.practicum.explorewithme.ewm.model.compilation.Compilation;
import ru.practicum.explorewithme.ewm.repository.compilation.CompilationRepository;
import ru.practicum.explorewithme.ewm.storage.compilation.CompilationStorage;

import java.util.List;

@Component("compilationDbStorageImpl")
@RequiredArgsConstructor
public class CompilationDbStorageImpl implements CompilationStorage {
    private final CompilationRepository compilationRepository;

    @Override
    public Compilation put(Compilation compilation) {
        return compilationRepository.saveAndFlush(compilation);
    }

    @Override
    public void delete(Integer compilationId) {
        compilationRepository.deleteById(compilationId);
    }

    @Override
    public Compilation update(Compilation compilation) {
        return compilationRepository.saveAndFlush(compilation);
    }

    @Override
    public Compilation getById(Integer id) {
        return compilationRepository.getReferenceById(id);
    }

    @Override
    public List<Compilation> findAllByPinned(Boolean pinned, Integer from, Integer size) {
        return compilationRepository.findAllByPinnedIs(pinned, PageRequest.of(from, size)).getContent();
    }

    @Override
    public void checkCompilation(int id) {
        if (!compilationRepository.existsById(id)) {
            throw new UnknownDataException(String.format(COMPILATION_NOT_FOUND, id));
        }
    }
}
