package io.m2i.recipee.dao;

import io.m2i.recipee.model.Recipe;
import io.m2i.recipee.model.Tag;

import java.util.List;

public interface TagDAO extends GenericDAO<Tag, Long> {

    Tag getByName(String name);

    List<Tag> getTagsPerRecipeId(Long recipeId);

}
