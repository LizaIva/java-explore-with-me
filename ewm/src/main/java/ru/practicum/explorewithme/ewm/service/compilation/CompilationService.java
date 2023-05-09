package ru.practicum.explorewithme.ewm.service.compilation;

import ru.practicum.explorewithme.ewm.dto.compilation.CompilationDto;
import ru.practicum.explorewithme.ewm.dto.compilation.CreateCompilationDto;

import java.util.List;

public interface CompilationService {
    CompilationDto put(CreateCompilationDto createCompilationDto);

    void delete(Integer compilationId);

    CompilationDto update(CreateCompilationDto compilationDto, Integer id);

    List<CompilationDto> getAllByPinned(Boolean pinned, Integer from, Integer size);

    CompilationDto getById(Integer compilationId);
}
