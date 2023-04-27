package io.m2i.recipee.servlet.recipes;

import io.m2i.recipee.model.Category;
import io.m2i.recipee.model.Recipe;
import io.m2i.recipee.service.CategoryService;
import io.m2i.recipee.service.RecipeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = RecipeEditServlet.URL)
public class RecipeEditServlet extends HttpServlet {

    public static final String URL = "/edit-recipe";
    private static final String JSP = "/WEB-INF/recipes/recipe-form.jsp";
    private final RecipeService recipeService = new RecipeService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        Recipe recipe = recipeService.getRecipeById(id);
        Category category = categoryService.getCategoryByName(recipe.getCategory().getName());
        List<Category> categoryList = categoryService.findAllCategories();

        req.setAttribute("recipe", recipe);
        req.setAttribute("currentCategory", category);
        req.setAttribute("categories", categoryList);

        req.setAttribute("isEdit", "isEdit");

        req.getRequestDispatcher(RecipeEditServlet.JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String ingredients = req.getParameter("ingredients");
        int preparationTime = Integer.parseInt(req.getParameter("preparationTime"));
        String instructions = req.getParameter("instructions");
        int cookingTime = Integer.parseInt(req.getParameter("cookingTime"));
        Category category = categoryService.getCategoryById(Long.valueOf(req.getParameter("category")));

        if ( name.isBlank() || ingredients.isBlank() || instructions.isBlank() ) {
            req.setAttribute("editError", "Empty name, ingredients or instructions is not allowed");
        }

        boolean success = recipeService.updateRecipeById(id, name, ingredients, preparationTime,
                instructions, cookingTime, category, null);

        if (!success) {
            req.setAttribute("editError", "Error while editing recipe");
        }

        resp.sendRedirect(RecipeListServlet.URL);

    }
}
