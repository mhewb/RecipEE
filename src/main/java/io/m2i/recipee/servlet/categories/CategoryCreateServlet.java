package io.m2i.recipee.servlet.categories;

import io.m2i.recipee.model.Category;
import io.m2i.recipee.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = CategoryCreateServlet.URL)
public class CategoryCreateServlet extends HttpServlet {

    public static final String URL = "/create-category";
    private static final String JSP = "/WEB-INF/categories/category-form.jsp";
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(CategoryCreateServlet.JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("categoryName");

        if ( name.isBlank() ) {
            req.setAttribute("createError", "Empty name is not allowed");
        }

        Category category = categoryService.createCategory(name);
        if (category == null) {
            req.setAttribute("createError", "Error while creating category");
        }

        req.getRequestDispatcher(CategoryCreateServlet.JSP).forward(req, resp);

    }
}
