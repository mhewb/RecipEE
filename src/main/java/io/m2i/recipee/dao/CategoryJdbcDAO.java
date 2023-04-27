package io.m2i.recipee.dao;

import io.m2i.recipee.ConnectionManager;
import io.m2i.recipee.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryJdbcDAO implements CategoryDAO {

    Connection con = ConnectionManager.getInstance();

    private Category mapToCategory(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");

        return new Category(id, name);
    }

    @Override
    public Category create(Category entity) {

        String sqlQuery = "INSERT INTO Categories(name) VALUES (?)";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, entity.getName());
            int row = pst.executeUpdate();

            if (row == 1) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Long id = generatedKeys. getLong(1);
                    entity.setId(id);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not create Category.", e);
        }

        return entity;

    }

    @Override
    public List<Category> findAll() {

        List<Category> categoryList = new ArrayList<>();
        String sqlQuery = "SELECT id, name FROM Categories";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Category category = mapToCategory(rs);
                categoryList.add(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not find Categories.", e);
        }
        return categoryList;
    }

    @Override
    public Category getById(Long aLong) {

        Category categoryFound = null;
        String sqlQuery = "SELECT id, name FROM Categories WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sqlQuery)) {

            ps.setLong(1, aLong);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    categoryFound = mapToCategory(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not find Category", e);
        }
        return categoryFound;
    }

    public Category getByName(String name) {

        Category categoryFound = null;
        String sqlQuery = "SELECT id, name FROM Categories WHERE name = ?";

        try (PreparedStatement ps = con.prepareStatement(sqlQuery)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    categoryFound = mapToCategory(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not find Category with name " + name, e);
        }
        return categoryFound;
    }

    @Override
    public boolean update(Category entity) {

        String sqlQuery = "UPDATE Categories SET name = ? WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            pst.setString(1, entity.getName());
            pst.setLong(2, entity.getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean delete(Category entity) {

        String sqlQuery = "DELETE FROM Categories WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            pst.setLong(1, entity.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Could not delete Category.", e);
        }
        return true;
    }

}
