package io.m2i.recipee.servlet.recipes;

import io.m2i.recipee.dao.RecipeDAO;
import io.m2i.recipee.dao.RecipeJdbcDAO;
import io.m2i.recipee.model.Recipe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = RecipeDetailsServlet.URL)
public class RecipeDetailsServlet extends HttpServlet {

    protected static final String URL = "/detail-recipe";
    private static final String JSP = "/WEB-INF/recipes/detail-recipe.jsp";
    private final RecipeDAO recipeDAO = new RecipeJdbcDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));

        Recipe recipe = recipeDAO.getById(id);

        req.setAttribute("recipe", recipe);

        req.getRequestDispatcher(JSP).forward(req, resp);

    }
}