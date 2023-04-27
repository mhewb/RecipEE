package io.m2i.recipee.dao;

import io.m2i.recipee.model.Recipe;
import io.m2i.recipee.model.User;

import java.util.Date;

public interface UserDAO extends GenericDAO<User, Long> {

    User getByEmail(String email);
    Date getLastTimeRecipeCooked(User user, Recipe recipe);
    boolean updateLastTimeRecipeCooked(User user, Recipe recipe);

}
