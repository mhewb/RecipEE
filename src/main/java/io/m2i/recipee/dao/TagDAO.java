package io.m2i.recipee.dao;

import io.m2i.recipee.model.Tag;

public interface TagDAO extends GenericDAO<Tag, Long> {

    Tag getByName(String name);


}
