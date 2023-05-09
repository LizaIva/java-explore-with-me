package ru.practicum.explorewithme.ewm.utils.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.ewm.dto.category.CategoryDto;
import ru.practicum.explorewithme.ewm.dto.category.CreateCategoryDto;
import ru.practicum.explorewithme.ewm.model.category.Category;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    public CategoryDto mapToCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category mapToCategory(CreateCategoryDto createCategoryDto) {
        return Category.builder()
                .name(createCategoryDto.getName())
                .build();
    }

    public List<CategoryDto> mapToCategoriesDto(List<Category> categories) {
        List<CategoryDto> categoriesDto = new ArrayList<>();
        for (Category category : categories) {
            categoriesDto.add(mapToCategoryDto(category));
        }
        return categoriesDto;
    }
}
