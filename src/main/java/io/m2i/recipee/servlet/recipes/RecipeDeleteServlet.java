package io.m2i.recipee.servlet.recipes;

import io.m2i.recipee.service.RecipeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = RecipeDeleteServlet.URL)
public class RecipeDeleteServlet extends HttpServlet {

    public static final String URL = "/delete-recipe";
//    private static final String JSP = "";
    private RecipeService recipeService = new RecipeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        recipeService.deleteRecipeById(id);

        req.getRequestDispatcher(RecipeListServlet.URL).forward(req, resp);

    }
    

}
