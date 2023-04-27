package io.m2i.recipee.dao;

import io.m2i.recipee.model.Category;
import io.m2i.recipee.model.Recipe;
import io.m2i.recipee.model.Tag;
import io.m2i.recipee.model.User;

import java.time.LocalDate;
import java.util.List;

public interface RecipeDAO extends GenericDAO<Recipe, Long> {

    Recipe getByName(String name);
    List<Recipe> getByCategory(Category category);
    List<Recipe> getByTag(Tag tag);
    LocalDate getLastTimeRecipeCooked(User user, Recipe recipe);
    boolean updateLastTimeRecipeCooked(User user, Recipe recipe);
    List<Recipe> searchRecipe(String query);
    Recipe getRandomRecipeOlderThanXDays(User user, int days);

}
