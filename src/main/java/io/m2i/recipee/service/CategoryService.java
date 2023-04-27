package io.m2i.recipee.service;

import io.m2i.recipee.dao.CategoryDAO;
import io.m2i.recipee.dao.CategoryJdbcDAO;
import io.m2i.recipee.model.Category;

import java.util.List;

public class CategoryService {

    private CategoryDAO categoryDAO = new CategoryJdbcDAO();

    public List<Category> findAllCategories() {
        return categoryDAO.findAll();
    }
    public Category getCategoryById(Long id) {
        return categoryDAO.getById(id);
    }

    public Category getCategoryByName(String name) {
        return categoryDAO.getByName(name);
    }

    public Category createCategory(String name) {
        Category category = new Category(name);
        return categoryDAO.create(category);
    }

    public boolean updateCategoryById(Long id, String name) {
        Category category = categoryDAO.getById(id);
        category.setName(name);
        return categoryDAO.update(category);
    }

    public boolean deleteCategoryById(Long id) {
        Category category = categoryDAO.getById(id);
        return categoryDAO.delete(category);
    }

}
