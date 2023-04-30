package ru.practicum.explorewithme.ewm.utils.compilation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.ewm.dto.compilation.CompilationDto;
import ru.practicum.explorewithme.ewm.dto.compilation.CreateCompilationDto;
import ru.practicum.explorewithme.ewm.model.compilation.Compilation;
import ru.practicum.explorewithme.ewm.repository.event.EventRepository;
import ru.practicum.explorewithme.ewm.storage.event.EventStorage;
import ru.practicum.explorewithme.ewm.utils.event.EventMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompilationMapper {

    private final EventStorage eventStorage;
    private final EventMapper eventMapper;

    public CompilationDto mapToCompilationDto(Compilation compilation) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .events(eventMapper.mapToEventsDto(compilation.getEvents()))
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
    }

    public Compilation mapToCompilation(CreateCompilationDto createCompilationDto) {
        return Compilation.builder()
                .pinned(createCompilationDto.getPinned())
                .title(createCompilationDto.getTitle())
                .events(eventStorage.findAllByIdIn(createCompilationDto.getEvents()))
                .build();
    }

    public List<CompilationDto> mapToCompilationsDto(List<Compilation> compilations) {
        return compilations.stream()
                .map(this::mapToCompilationDto)
                .collect(Collectors.toList());
    }
}
