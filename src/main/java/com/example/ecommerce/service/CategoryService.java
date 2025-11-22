package com.example.ecommerce.service;

import com.example.ecommerce.model.dto.CategoryDto;
import com.example.ecommerce.model.entity.Category;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto saveCategory(CategoryDto dto) {
        Category category = Mapper.toCategoryEntity(dto);
        category.setStatus(true);
        return Mapper.toCategoryDto(categoryRepository.save(category));
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .filter(Category::isStatus)
                .map(Mapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        if (!category.isStatus()) throw new RuntimeException("Category deleted");
        return Mapper.toCategoryDto(category);
    }

    public CategoryDto getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        if (!category.isStatus()) throw new RuntimeException("Category deleted");
        return Mapper.toCategoryDto(category);
    }

    public CategoryDto updateCategory(CategoryDto dto) {
        Category category = categoryRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return Mapper.toCategoryDto(categoryRepository.save(category));
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setStatus(false);
        categoryRepository.save(category);
    }
}
