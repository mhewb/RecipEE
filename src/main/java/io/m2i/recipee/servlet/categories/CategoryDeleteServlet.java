package io.m2i.recipee.servlet.categories;

import io.m2i.recipee.service.CategoryService;
import io.m2i.recipee.servlet.categories.CategoryListServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = CategoryDeleteServlet.URL)
public class CategoryDeleteServlet extends HttpServlet {

    public static final String URL = "/delete-category";
//    private static final String JSP = "";
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        categoryService.deleteCategoryById(id);

        req.getRequestDispatcher(CategoryListServlet.URL).forward(req, resp);

    }
    

}
