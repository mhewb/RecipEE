package io.m2i.recipee.service;

import io.m2i.recipee.dao.TagDAO;
import io.m2i.recipee.dao.TagJdbcDAO;
import io.m2i.recipee.model.Tag;

import java.util.List;

public class TagService {

    private TagDAO tagDAO = new TagJdbcDAO();

    public List<Tag> findAllTags() {
        return tagDAO.findAll();
    }
    public Tag getTagById(Long id) {
        return tagDAO.getById(id);
    }

    public Tag getTagByName(String name) {
        return tagDAO.getByName(name);
    }

    public Tag createTag(String name) {
        Tag tag = new Tag(name);
        return tagDAO.create(tag);
    }

    public boolean updateTagById(Long id, String name) {
        Tag tag = tagDAO.getById(id);
        tag.setName(name);
        return tagDAO.update(tag);
    }

    public boolean deleteTagById(Long id) {
        Tag tag = tagDAO.getById(id);
        return tagDAO.delete(tag);
    }

}
