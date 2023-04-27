package ru.practicum.explorewithme.ewm.service.category.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.ewm.dto.category.CategoryDto;
import ru.practicum.explorewithme.ewm.dto.category.CreateCategoryDto;
import ru.practicum.explorewithme.ewm.model.category.Category;
import ru.practicum.explorewithme.ewm.service.category.CategoryService;
import ru.practicum.explorewithme.ewm.storage.categoty.CategoryStorage;
import ru.practicum.explorewithme.ewm.utils.category.CategoryMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryStorage categoryStorage;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto put(CreateCategoryDto categoryDto) {
        Category createCategory = categoryMapper.mapToCategory(categoryDto);
        Category actualCategory = categoryStorage.put(createCategory);
        log.info("Create category");
        return categoryMapper.mapToCategoryDto(actualCategory);
    }

    @Override
    public void delete(Integer categoryId) {
        categoryStorage.checkCategory(categoryId);
        log.info("Delete category with id = {}", categoryId);
        categoryStorage.delete(categoryId);
    }

    @Override
    public CategoryDto update(CreateCategoryDto categoryDto, Integer id) {
        categoryStorage.checkCategory(id);
        Category category = categoryStorage.getById(id);
        category.setName(categoryDto.getName());
        log.info("Update category");

        Category actualCategory = categoryStorage.update(category);
        return categoryMapper.mapToCategoryDto(actualCategory);
    }

    @Override
    public CategoryDto getById(Integer categoryId) {
        return categoryMapper.mapToCategoryDto(categoryStorage.getById(categoryId));
    }

    @Override
    public List<CategoryDto> getAll(Integer from, Integer size) {
        return categoryMapper.mapToCategoriesDto(categoryStorage.getAll(from, size));
    }
}
