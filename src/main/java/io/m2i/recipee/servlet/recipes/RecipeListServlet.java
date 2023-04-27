package io.m2i.recipee.servlet.recipes;

import io.m2i.recipee.model.Recipe;
import io.m2i.recipee.model.User;
import io.m2i.recipee.service.RecipeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = RecipeListServlet.URL)
public class RecipeListServlet extends HttpServlet {

    public static final String URL = "/recipes-list";
    private static final String JSP = "/WEB-INF/recipes/recipes-list.jsp";
    private final RecipeService recipeService = new RecipeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");

        List<Recipe> recipeList = recipeService.findAllRecipes();

        if (loggedUser != null) {
            for (Recipe recipe : recipeList) {
                recipe.setLastCookedDate(recipeService.getLastTimeRecipeCooked(loggedUser, recipe));
            }
        }

        req.setAttribute("recipes", recipeList);

        req.getRequestDispatcher(JSP).forward(req, resp);

    }
}
