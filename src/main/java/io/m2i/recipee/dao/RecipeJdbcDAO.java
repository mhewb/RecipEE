package io.m2i.recipee.dao;

import io.m2i.recipee.ConnectionManager;
import io.m2i.recipee.model.Category;
import io.m2i.recipee.model.Recipe;
import io.m2i.recipee.model.Tag;
import io.m2i.recipee.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecipeJdbcDAO implements RecipeDAO {

    Connection con = ConnectionManager.getInstance();
    TagDAO tagDAO = new TagJdbcDAO();
    CategoryDAO categoryDAO = new CategoryJdbcDAO();

    private Recipe mapToRecipe(ResultSet rs) throws SQLException {

        Long id = rs.getLong("id");
        String name = rs.getString("name");
        String ingredients = rs.getString("ingredients");
        int preparationTime = rs.getInt("preparationTime");
        String instructions = rs.getString("instructions");
        int cookingTime = rs.getInt("cookingTime");
        Long idCategory = rs.getLong("idCategory");

        Category category = categoryDAO.getById(idCategory);
        List<Tag> tags = tagDAO.getTagsPerRecipeId(id);

        return new Recipe(id, name, ingredients, preparationTime, instructions, cookingTime, category, tags);
    }

    @Override
    public Recipe create(Recipe entity) {

        String sqlQueryCreateRecipe = "INSERT INTO Recipes(name, ingredients, preparationTime, instructions, cookingTime, idCategory) VALUES (?,?,?,?,?,?)";
        String sqlQueryCreateRecipeTags = "INSERT INTO RecipeTags(idRecipes, idTags) VALUES (?,?)";

        try (PreparedStatement pstCreateRecipe = con.prepareStatement(sqlQueryCreateRecipe, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement pstCreateRecipeTags = con.prepareStatement(sqlQueryCreateRecipeTags)) {

            pstCreateRecipe.setString(1, entity.getName());
            pstCreateRecipe.setString(2, entity.getIngredients());
            pstCreateRecipe.setInt(3, entity.getPreparationTime());
            pstCreateRecipe.setString(4, entity.getInstructions());
            pstCreateRecipe.setInt(5, entity.getCookingTime());
            pstCreateRecipe.setLong(6, entity.getCategory().getId());

            int row = pstCreateRecipe.executeUpdate();

            if (row == 1) {
                ResultSet generatedKeys = pstCreateRecipe.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Long id = generatedKeys. getLong(1);
                    entity.setId(id);
                }
            }

            if (entity.getTags() != null) {

                for (Tag tag : entity.getTags()) {
                    pstCreateRecipeTags.setLong(1, entity.getId());
                    pstCreateRecipeTags.setLong(2, tag.getId());
                    pstCreateRecipeTags.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not create Recipe.", e);
        }

        return entity;

    }

    @Override
    public List<Recipe> findAll() {

        List<Recipe> recipeList = new ArrayList<>();
        String sqlQuery = "SELECT id, name, ingredients, preparationTime, instructions, cookingTime, idCategory FROM Recipes";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Recipe recipe = mapToRecipe(rs);
                recipeList.add(recipe);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not find Recipes.", e);
        }
        return recipeList;
    }

    @Override
    public Recipe getById(Long aLong) {

        Recipe recipeFound = null;
        String sqlQuery = "SELECT id, name, ingredients, preparationTime, instructions, cookingTime, idCategory FROM Recipes WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            pst.setLong(1, aLong);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    recipeFound = mapToRecipe(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not find Recipe", e);
        }
        return recipeFound;
    }

    public Recipe getByName(String name) {

        Recipe recipeFound = null;
        String sqlQuery = "SELECT id, name, ingredients, preparationTime, instructions, cookingTime, idCategory FROM Recipes WHERE name = ?";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            pst.setString(1, name);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    recipeFound = mapToRecipe(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not find Recipe with name " + name, e);
        }
        return recipeFound;
    }

    @Override
    public List<Recipe> getByCategory(Category category) {

        List<Recipe> recipeList = new ArrayList<>();

        String sqlQuery =
                "SELECT r.id, r.name, r.ingredients, r.preparationTime, r.instructions, r.cookingTime, c.id AS idCategory FROM Recipes r INNER JOIN Categories c on r.idCategory = c.id WHERE c.id = ?;";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            pst.setLong(1, category.getId());

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Recipe recipe = mapToRecipe(rs);
                recipeList.add(recipe);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not find Recipe associated to Category id=" + category.getId(), e);
        }
        return recipeList;
    }

    @Override
    public List<Recipe> getByTag(Tag tag) {
        List<Recipe> recipeList = new ArrayList<>();

        String sqlQuery =
                "SELECT r.name, r.ingredients, r.preparationTime, r.instructions, r.cookingTime, r.idCategory, rt.idRecipes " +
                "FROM RecipeTags rt INNER JOIN Recipes r ON rt.idRecipes = r.id WHERE rt.idTags = ?;";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            pst.setLong(1, tag.getId());

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Recipe recipe = mapToRecipe(rs);
                recipeList.add(recipe);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not find Recipe associated to Category id=" + tag.getId(), e);
        }
        return recipeList;
    }

    @Override
    public boolean update(Recipe entity) {

        String sqlQueryUpdateRecipe = "UPDATE Recipes SET " +
                "name = ?, " +
                "ingredients = ?, " +
                "preparationTime = ?, " +
                "instructions = ?, " +
                "cookingTime = ?, " +
                "idCategory = ? " +
                "WHERE id = ?";

        // TODO: refactor, put this in TagDAO ?
        String sqlQueryDeleteRecipeTags = "DELETE FROM RecipeTags WHERE idRecipes = ?";
        String sqlQueryCreateRecipeTags = "INSERT INTO RecipeTags(idRecipes, idTags) VALUES (?,?)";

        try (PreparedStatement pstUpdateRecipe = con.prepareStatement(sqlQueryUpdateRecipe);
             PreparedStatement pstDeleteRecipeTags = con.prepareStatement(sqlQueryDeleteRecipeTags);
             PreparedStatement pstCreateRecipeTags = con.prepareStatement(sqlQueryCreateRecipeTags)) {

            con.setAutoCommit(false); // To avoid updating the Recipes Table if any pbm happens while deleting/creating tags

            pstUpdateRecipe.setString(1, entity.getName());
            pstUpdateRecipe.setString(2, entity.getIngredients());
            pstUpdateRecipe.setInt(3, entity.getPreparationTime());
            pstUpdateRecipe.setString(4, entity.getInstructions());
            pstUpdateRecipe.setInt(5, entity.getCookingTime());
            pstUpdateRecipe.setLong(6, entity.getCategory().getId());
            pstUpdateRecipe.setLong(7, entity.getId());

            pstUpdateRecipe.executeUpdate();

            // Delete Recipe Tags
            pstDeleteRecipeTags.setLong(1, entity.getId());
            pstDeleteRecipeTags.executeUpdate();

            // Insert Recipe Tags
            for (Tag tag: entity.getTags()) {
                pstCreateRecipeTags.setLong(1,entity.getId());
                pstCreateRecipeTags.setLong(2,tag.getId());
                pstCreateRecipeTags.executeUpdate();
            }

            con.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
        // TODO: con.setAutoCommit(true); ?
    }

    @Override
    public boolean delete(Recipe entity) {

        String sqlQuery = "DELETE FROM Recipes WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            pst.setLong(1, entity.getId());
            pst.executeUpdate();

            // DELETE RecipeTags automated from DB

        } catch (SQLException e) {
            throw new RuntimeException("Could not delete Recipe.", e);
        }
        return true;
    }

    public List<Recipe> searchRecipe(String query) {

        List<Recipe> recipeList = new ArrayList<>();
        String sqlQuery =
                "SELECT DISTINCT r.* " +
                "FROM Categories c " +
                "LEFT JOIN Recipes r ON c.id = r.idCategory " +
                "LEFT JOIN RecipeTags rt ON r.id = rt.idRecipes " +
                "LEFT JOIN Tags t ON rt.idTags = t.id " +
                "WHERE c.name LIKE ? " +
                "OR r.name LIKE ? " +
                "OR t.name LIKE ?";

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            String searchTerm = "%" + query + "%";

            pst.setString(1, searchTerm);
            pst.setString(2, searchTerm);
            pst.setString(3, searchTerm);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Recipe recipe = mapToRecipe(rs);
                    recipeList.add(recipe);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not find Recipe", e);
        }
        return recipeList;
    }

    public Recipe getRandomRecipeOlderThanXDays(User user, int days) {

        String sqlQuery =
                "SELECT r.id, r.name, r.ingredients, r.preparationTime, r.instructions, r.cookingTime, " +
                "r.idCategory, ur.lastCooked, ur.idUser FROM Recipes r JOIN UserRecipe ur " +
                "WHERE NOT EXISTS ( SELECT 1 FROM UserRecipe ur WHERE ur.idRecipes = r.id AND ur.idUser = ? "+
                "AND ur.lastCooked >= DATE_SUB(CURDATE(), INTERVAL ? DAY) ) ORDER BY RAND() LIMIT 1";
        Recipe recipeFound = null;

        try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {

            pst.setLong(1, user.getId());
            pst.setInt(2, days);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    recipeFound = mapToRecipe(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not find a recipe that was cooked less than " + days + " days ago by User id=" + user.getId(), e);
        }
        return recipeFound;
    }

}
