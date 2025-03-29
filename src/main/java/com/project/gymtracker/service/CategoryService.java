package com.project.gymtracker.service;

import com.project.gymtracker.entity.Category;
import com.project.gymtracker.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name).orElse(null);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    @Transactional
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
