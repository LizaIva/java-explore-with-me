package ru.practicum.explorewithme.ewm.storage.categoty.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.ewm.exception.UnknownDataException;
import ru.practicum.explorewithme.ewm.model.category.Category;
import ru.practicum.explorewithme.ewm.repository.category.CategoryRepository;
import ru.practicum.explorewithme.ewm.storage.categoty.CategoryStorage;

import java.util.List;

@Component("categoryDbStorageImpl")
@RequiredArgsConstructor
public class CategoryDbStorageImpl implements CategoryStorage {

    private final CategoryRepository categoryRepository;

    @Override
    public Category put(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public void delete(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public Category getById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new UnknownDataException(""));
    }

    @Override
    public List<Category> getAll(Integer from, Integer size) {
        return categoryRepository.findAll(PageRequest.of(from, size)).getContent();
    }

    @Override
    public void checkCategory(int id) {
        if (!categoryRepository.existsById(id)) {
            throw new UnknownDataException(String.format(CATEGORY_NOT_FOUND, id));
        }
    }
}
