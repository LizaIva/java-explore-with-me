package ru.practicum.explorewithme.ewm.storage.categoty;

import ru.practicum.explorewithme.ewm.dto.category.CategoryDto;
import ru.practicum.explorewithme.ewm.dto.category.CreateCategoryDto;
import ru.practicum.explorewithme.ewm.model.category.Category;

import java.util.List;

public interface CategoryStorage {
    String CATEGORY_NOT_FOUND = "Category with id = %s not found.";
    Category put(Category category);

    void delete(Integer categoryId);

    Category update(Category category);

    Category getById(Integer id);

    List<Category> getAll(Integer from, Integer size);
    void checkCategory(int id);
}
