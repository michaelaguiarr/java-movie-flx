package dev.michaelaguiar.service;

import dev.michaelaguiar.dto.request.CategoryRequest;
import dev.michaelaguiar.dto.response.CategoryResponse;
import dev.michaelaguiar.entity.Category;
import dev.michaelaguiar.mapper.CategoryMapper;
import dev.michaelaguiar.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryResponse save(CategoryRequest request) {
        Category categoryEntity = CategoryMapper.toCategory(request);
        categoryEntity = categoryRepository.save(categoryEntity);
        return CategoryMapper.toCategoryResponse(categoryEntity);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return CategoryMapper.toCategoryResponse(category.get());
        }
        return null;
    }

    public Optional<Category> findByIdCategory(Long id) {
        return categoryRepository.findById(id);
    }


    @Transactional
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

}
