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

@WebServlet(urlPatterns = LandingPageServlet.URL)
public class LandingPageServlet extends HttpServlet {

    public static final String URL = "/";
    private static final String JSP = "/WEB-INF/landing.jsp";
    private final RecipeService recipeService = new RecipeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Recipe> recipeList = recipeService.findAllRecipes();
        req.setAttribute("recipes", recipeList);

        req.getRequestDispatcher(JSP).forward(req, resp);
    }

}