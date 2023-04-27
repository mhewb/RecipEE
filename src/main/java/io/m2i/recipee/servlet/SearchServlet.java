package io.m2i.recipee.servlet;

import io.m2i.recipee.model.Recipe;
import io.m2i.recipee.service.RecipeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = SearchServlet.URL)
public class SearchServlet extends HttpServlet {

    public static final String URL = "/search";
    private static final String JSP = "/WEB-INF/recipes/recipes-list.jsp";
    RecipeService recipeService = new RecipeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String query = req.getParameter("query");
        List<Recipe> recipeList = recipeService.searchRecipe(query);

        req.setAttribute("recipes", recipeList);
        req.setAttribute("searchQuery", query);

        req.getRequestDispatcher(JSP).forward(req, resp);

    }
}
