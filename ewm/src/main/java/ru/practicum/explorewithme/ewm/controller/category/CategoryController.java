package ru.practicum.explorewithme.ewm.controller.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.ewm.dto.category.CategoryDto;
import ru.practicum.explorewithme.ewm.service.category.CategoryService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{catId}")
    public CategoryDto getById(@PathVariable Integer catId) {
        log.info("Request category by id{}", catId);
        return categoryService.getById(catId);
    }


    @GetMapping
    public List<CategoryDto> getAll(@RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                    @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Request all categories from {} size {}", from, size);
        return categoryService.getAll(from, size);
    }
}
