package io.m2i.recipee.service;

import io.m2i.recipee.model.User;
import io.m2i.recipee.dao.UserDAO;
import io.m2i.recipee.dao.UserJdbcDAO;

import java.util.List;

public class UserService {

    private final UserDAO userDAO = new UserJdbcDAO();

    public List<User> findAllUsers() {
        return userDAO.findAll();
    }
    public User getUserById(Long id) {
        return userDAO.getById(id);
    }

    public User getUserByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    // TODO: create UserAlreadyExist Exception!
    public boolean createUser(String email, String password, String firstName, String lastName, String avatarUrl) {
        if (getUserByEmail(email) != null) {
            return false;
        }
        User user = new User(email, password, firstName, lastName, avatarUrl);
        User createdUser = userDAO.create(user);
        if (createdUser != null) {
            return true;
        } else {
            return false;
        }

    }

    public boolean updateUserById(Long id, String email, String password, String firstName, String lastName, String avatarUrl) {
        User user = userDAO.getById(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAvatarUrl(avatarUrl);
        return userDAO.update(user);
    }

    public boolean deleteUserById(Long id) {
        User user = userDAO.getById(id);
        return userDAO.delete(user);
    }

    public boolean checkLog(String email, String password) {
        User userFound = userDAO.getByEmail(email);
        if (userFound != null) {
            return userFound.getPassword().equals(password);
        }
        return false;
    }

}
