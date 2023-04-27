package io.m2i.recipee.service;

import io.m2i.recipee.dao.RecipeDAO;
import io.m2i.recipee.dao.RecipeJdbcDAO;
import io.m2i.recipee.model.Category;
import io.m2i.recipee.model.Recipe;
import io.m2i.recipee.model.Tag;
import io.m2i.recipee.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecipeService {

    private RecipeDAO recipeDAO = new RecipeJdbcDAO();

    public List<Recipe> findAllRecipes() {
        return recipeDAO.findAll();
    }
    public Recipe getRecipeById(Long id) {
        return recipeDAO.getById(id);
    }

    public Recipe getRecipeByName(String name) {
        return recipeDAO.getByName(name);
    }

    public List<Recipe> getRecipesByTag(Tag tag) {
        return recipeDAO.getByTag(tag);
    }

    public List<Recipe> getRecipesByCategory(Category category) {
        return recipeDAO.getByCategory(category);
    }

    public Recipe createRecipe(String name, String ingredients, int preparationTime, String instructions, int cookingTime, Category category, List<Tag> tags) {
        Recipe recipe = new Recipe(name, ingredients, preparationTime, instructions, cookingTime, category, tags);
        return recipeDAO.create(recipe);
    }

    public boolean updateRecipeById(Long id, String name, String ingredients, int preparationTime, String instructions, int cookingTime, Category category, List<Tag> tags) {
        Recipe recipe = recipeDAO.getById(id);
        recipe.setName(name);
        return recipeDAO.update(recipe);
    }

    public boolean deleteRecipeById(Long id) {
        Recipe recipe = recipeDAO.getById(id);
        return recipeDAO.delete(recipe);
    }

    public List<Recipe> searchRecipe(String query) {
        return recipeDAO.searchRecipe(query);
    }

    public Recipe getRandomRecipeOlderThanXDays(User user, int days) {
        return recipeDAO.getRandomRecipeOlderThanXDays(user, days);
    }

}
