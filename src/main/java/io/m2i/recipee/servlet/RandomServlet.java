package io.m2i.recipee.servlet;

import io.m2i.recipee.dao.RecipeDAO;
import io.m2i.recipee.dao.RecipeJdbcDAO;
import io.m2i.recipee.dao.UserDAO;
import io.m2i.recipee.dao.UserJdbcDAO;
import io.m2i.recipee.model.Recipe;
import io.m2i.recipee.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = RandomServlet.URL)
public class RandomServlet extends HttpServlet {

    public static final String URL = "/random";
    private static final String JSP = "/WEB-INF/recipes/recipes-list.jsp";
    final RecipeDAO recipeDAO = new RecipeJdbcDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");

        Recipe recipe = recipeDAO.getRandomRecipeOlderThanXDays(loggedUser,6);
        List<Recipe> listRecipe = new ArrayList<>();
        listRecipe.add(recipe);

        req.setAttribute("recipes", listRecipe);

        req.getRequestDispatcher(JSP).forward(req, resp);

    }
}
