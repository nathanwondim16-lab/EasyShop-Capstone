package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.exceptions.CategoryNotFoundException;
import org.yearup.exceptions.InvalidCategoryException;
import org.yearup.models.Category;
import org.yearup.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService
{
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories()
    {
        // get all categories
        return categoryRepository.findAll();
    }

    public Category getById(Integer categoryId)
    {
        // get category by id
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    public Category create(Category category)
    {
        // create a new category
        if(category == null) {
            throw new InvalidCategoryException("Invalid category");
        }


        return categoryRepository.save(category);
    }

    public Category update(Integer categoryId, Category category)
    {
        // update category and return the updated category
        var selectedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));

        selectedCategory.setName(category.getName());
        selectedCategory.setDescription(category.getDescription());
        categoryRepository.save(selectedCategory);

        return selectedCategory;
    }

    public void delete(Integer categoryId)
    {
        // delete category
        var category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        categoryRepository.delete(category);
    }
}
