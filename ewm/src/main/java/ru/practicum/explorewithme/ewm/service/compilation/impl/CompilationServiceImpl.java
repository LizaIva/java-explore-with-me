package ru.practicum.explorewithme.ewm.service.compilation.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.ewm.dto.compilation.CompilationDto;
import ru.practicum.explorewithme.ewm.dto.compilation.CreateCompilationDto;
import ru.practicum.explorewithme.ewm.model.compilation.Compilation;
import ru.practicum.explorewithme.ewm.service.compilation.CompilationService;
import ru.practicum.explorewithme.ewm.storage.compilation.CompilationStorage;
import ru.practicum.explorewithme.ewm.storage.event.EventStorage;
import ru.practicum.explorewithme.ewm.utils.compilation.CompilationMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationStorage compilationStorage;
    private final CompilationMapper compilationMapper;
    private final EventStorage eventStorage;

    @Override
    public CompilationDto put(CreateCompilationDto createCompilationDto) {
        Compilation createCompilation = compilationMapper.mapToCompilation(createCompilationDto);
        Compilation actualCompilation = compilationStorage.put(createCompilation);
        log.info("Create compilation");
        return compilationMapper.mapToCompilationDto(actualCompilation);
    }

    @Override
    public void delete(Integer compilationId) {
        compilationStorage.checkCompilation(compilationId);
        log.info("Delete compilation with id = {}", compilationId);
        compilationStorage.delete(compilationId);
    }

    @Override
    public CompilationDto update(CreateCompilationDto compilationDto, Integer id) {
        compilationStorage.checkCompilation(id);
        Compilation compilation = compilationStorage.getById(id);

        if (compilationDto.getPinned() != null) {
            compilation.setPinned(compilationDto.getPinned());
        }
        if (compilationDto.getTitle() != null) {
            compilation.setTitle(compilationDto.getTitle());
        }
        if (compilationDto.getEvents() != null) {
            compilation.setEvents(eventStorage.findAllByIdIn(compilationDto.getEvents()));
        }
        log.info("Update category");

        compilationStorage.update(compilation);
        return compilationMapper.mapToCompilationDto(compilation);
    }

    @Override
    public List<CompilationDto> getAllByPinned(Boolean pinned, Integer from, Integer size) {
        List<Compilation> compilations = compilationStorage.findAllByPinned(pinned, from, size);
        return compilationMapper.mapToCompilationsDto(compilations);
    }

    @Override
    public CompilationDto getById(Integer compilationId) {
        compilationStorage.checkCompilation(compilationId);
        return compilationMapper.mapToCompilationDto(compilationStorage.getById(compilationId));
    }
}
