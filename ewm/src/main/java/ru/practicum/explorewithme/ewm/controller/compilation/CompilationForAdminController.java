package ru.practicum.explorewithme.ewm.controller.compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.ewm.dto.compilation.CompilationDto;
import ru.practicum.explorewithme.ewm.dto.compilation.CreateCompilationDto;
import ru.practicum.explorewithme.ewm.service.compilation.CompilationService;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin/compilations")
public class CompilationForAdminController {
    private final CompilationService compilationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto create(@RequestBody @Valid CreateCompilationDto createCompilationDto) {
        log.info("Create compilation");
        return compilationService.put(createCompilationDto);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer compId) {
        log.info("Delete compilation with id = {}", compId);
        compilationService.delete(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto update(@RequestBody CreateCompilationDto createCompilationDto,
                                 @PathVariable Integer compId) {
        log.info("Update category with id = {}", compId);
        return compilationService.update(createCompilationDto, compId);
    }
}
