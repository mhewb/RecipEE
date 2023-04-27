package io.m2i.recipee.dao;

import io.m2i.recipee.model.Category;

public interface CategoryDAO extends GenericDAO<Category, Long> {

    Category getByName(String name);


}
