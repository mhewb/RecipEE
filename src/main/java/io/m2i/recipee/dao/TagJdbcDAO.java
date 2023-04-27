package io.m2i.recipee.dao;

import io.m2i.recipee.ConnectionManager;
import io.m2i.recipee.model.Recipe;
import io.m2i.recipee.model.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagJdbcDAO implements TagDAO {

    Connection con = ConnectionManager.getInstance();

    private Tag mapToTag(ResultSet rs) throws SQLException {

        Long id = rs.getLong("id");
        String name = rs.getString("name");

        return new Tag(id, name);
    }

    @Override
    public Tag create(Tag entity) {

        String sqlQuery = "INSERT INTO Tags(name) VALUES (?)";

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
            throw new RuntimeException("Could not create Tag.", e);
        }

        return entity;

    }


    @Override
    public List<Tag> findAll() {

        List<Tag> tagList = new ArrayList<>();
        String sqlQuery = "SELECT id, name FROM Tags";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Tag tag = mapToTag(rs);
                tagList.add(tag);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not find Tags.", e);
        }
        return tagList;
    }

    @Override
    public List<Tag> getTagsPerRecipeId(Long idRecipe) {

        RecipeDAO recipeDAO = new RecipeJdbcDAO();
        List<Tag> tagList = new ArrayList<>();
        String sqlQuery = "SELECT t.name, rt.idRecipes, rt.idTags AS id  FROM RecipeTags rt INNER JOIN Tags t ON t.id = rt.idTags WHERE idRecipes = ?";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            pst.setLong(1, idRecipe);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Tag tag = mapToTag(rs);
                tagList.add(tag);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not find Tags for Recipe id=" + idRecipe, e);
        }
        return tagList;
    }

    @Override
    public Tag getById(Long aLong) {

        Tag tagFound = null;
        String sqlQuery = "SELECT id, name FROM Tags WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sqlQuery)) {

            ps.setLong(1, aLong);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tagFound = mapToTag(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not find Tag", e);
        }
        return tagFound;
    }

    public Tag getByName(String name) {

        Tag tagFound = null;
        String sqlQuery = "SELECT id, name FROM Tags WHERE name = ?";

        try (PreparedStatement ps = con.prepareStatement(sqlQuery)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tagFound = mapToTag(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not find Tag with name " + name, e);
        }
        return tagFound;
    }


    @Override
    public boolean update(Tag entity) {

        String sqlQuery = "UPDATE Tags SET name = ? WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            pst.setString(1, entity.getName());
            pst.setLong(2, entity.getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Could not update Tag id=" + entity.getId(), e);
        }
        return true;
    }

    @Override
    public boolean delete(Tag entity) {

        String sqlQuery = "DELETE FROM Tags WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            pst.setLong(1, entity.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Could not delete Tag id=" + entity.getId(), e);
        }
        return true;
    }

}
