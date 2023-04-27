package io.m2i.recipee.dao;

import io.m2i.recipee.ConnectionManager;
import io.m2i.recipee.model.Tag;
import io.m2i.recipee.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    Connection con = ConnectionManager.getInstance();

    private User mapToUser(ResultSet rs) throws SQLException {


        Long id = rs.getLong("id");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String avatarUrl = rs.getString("avatarUrl");

        return new User(id, email, password, firstName, lastName, avatarUrl);

    }
    @Override
    public User create(User entity) {
        String sqlQuery = "INSERT INTO Users(email, password, firstName, lastName, avatarURL) VALUES (?,?,?,?,?)";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, entity.getEmail());
            pst.setString(2, entity.getPassword());
            pst.setString(3, entity.getFirstName());
            pst.setString(4, entity.getLastName());
            pst.setString(5, entity.getAvatarUrl());

            int row = pst.executeUpdate();

            if (row == 1) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Long id = generatedKeys. getLong(1);
                    entity.setId(id);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not create User.", e);
        }

        return entity;

    }

    @Override
    public List<User> findAll() {

        List<User> userList = new ArrayList<>();
        String sqlQuery = "SELECT id, email, password, firstName, lastName, avatarURL FROM Users";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = mapToUser(rs);
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not find Users.", e);
        }
        return userList;
    }

    @Override
    public User getById(Long aLong) {
        User userFound = null;
        String sqlQuery = "SELECT id, email, password, firstName, lastName, avatarURL FROM Users WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sqlQuery)) {

            ps.setLong(1, aLong);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    userFound = mapToUser(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not find User", e);
        }
        return userFound;
    }

    public User getByEmail(String email) {
        User userFound = null;
        String sqlQuery = "SELECT id, email, password, firstName, lastName, avatarURL FROM Users WHERE email = ?";

        try (PreparedStatement ps = con.prepareStatement(sqlQuery)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    userFound = mapToUser(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not find User", e);
        }
        return userFound;
    }

    @Override
    public boolean update(User entity) {
        String sqlQuery = "UPDATE Users SET " +
                "email = ?, " +
                "password = ?, " +
                "firstName = ?, " +
                "lastName = ?, " +
                "avatarURL = ? "+
                "WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            pst.setString(1, entity.getEmail());
            pst.setString(2, entity.getPassword());
            pst.setString(3, entity.getFirstName());
            pst.setString(4, entity.getLastName());
            pst.setString(5, entity.getAvatarUrl());
            pst.setLong(6, entity.getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Could not update User id=" + entity.getId(), e);
        }
        return true;
    }

    @Override
    public boolean delete(User entity) {
        String sqlQuery = "DELETE FROM Users WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            pst.setLong(1, entity.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Could not delete User id=" + entity.getId(), e);
        }
        return true;
    }
}
