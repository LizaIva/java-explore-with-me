package ru.practicum.explorewithme.ewm.controller.compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.ewm.dto.compilation.CompilationDto;
import ru.practicum.explorewithme.ewm.service.compilation.CompilationService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/compilations")
public class CompilationController {
    private final CompilationService compilationService;

    @GetMapping
    public List<CompilationDto> getAllByPinned(@RequestParam(name = "pinned", required = false) Boolean pinned,
                                               @RequestParam(name = "from", required = false) Integer from,
                                               @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Request all compilations from {} size {} by pinned = {}", from, size, pinned);
        return compilationService.getAllByPinned(pinned, from, size);
    }

    @GetMapping("/{compId}")
    public CompilationDto deleteById(@PathVariable Integer compId) {
        log.info("Get compilation with id = {}", compId);
        return compilationService.getById(compId);
    }
}
