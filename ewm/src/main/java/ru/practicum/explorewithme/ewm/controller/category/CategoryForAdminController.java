package ru.practicum.explorewithme.ewm.controller.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.ewm.dto.category.CategoryDto;
import ru.practicum.explorewithme.ewm.dto.category.CreateCategoryDto;
import ru.practicum.explorewithme.ewm.service.category.CategoryService;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin/categories")
public class CategoryForAdminController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@RequestBody @Valid CreateCategoryDto createCategoryDto) {
        log.info("Create category");
        return categoryService.put(createCategoryDto);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer catId) {
        log.info("Delete category with id = {}", catId);
        categoryService.delete(catId);
    }

    @PatchMapping("/{catId}")
    public CategoryDto update(@RequestBody @Valid CreateCategoryDto createCategoryDto,
                              @PathVariable Integer catId) {
        log.info("Update category with id = {}", catId);
        return categoryService.update(createCategoryDto, catId);
    }
}
