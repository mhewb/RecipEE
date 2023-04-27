package io.m2i.recipee.dao;

import io.m2i.recipee.model.User;


public interface UserDAO extends GenericDAO<User, Long> {

    User getByEmail(String email);

}
