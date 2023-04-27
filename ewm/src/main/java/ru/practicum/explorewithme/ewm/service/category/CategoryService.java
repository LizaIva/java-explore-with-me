package ru.practicum.explorewithme.ewm.service.category;

import ru.practicum.explorewithme.ewm.dto.category.CategoryDto;
import ru.practicum.explorewithme.ewm.dto.category.CreateCategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto put(CreateCategoryDto categoryDto);

    void delete(Integer categoryId);

    CategoryDto update(CreateCategoryDto categoryDto, Integer id);

    CategoryDto getById(Integer categoryId);

    List<CategoryDto> getAll(Integer from, Integer size);
}
