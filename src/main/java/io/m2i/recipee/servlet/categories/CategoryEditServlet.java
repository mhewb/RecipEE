package io.m2i.recipee.servlet.categories;

import io.m2i.recipee.model.Category;
import io.m2i.recipee.service.CategoryService;
import io.m2i.recipee.servlet.categories.CategoryListServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = CategoryEditServlet.URL)
public class CategoryEditServlet extends HttpServlet {

    public static final String URL = "/edit-category";
    private static final String JSP = "/WEB-INF/categories/category-form.jsp";
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        Category category = categoryService.getCategoryById(id);

        req.setAttribute("category", category);
        req.setAttribute("isEdit", "isEdit");

        req.getRequestDispatcher(CategoryEditServlet.JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("categoryName");

        if ( name.isBlank() ) {
            req.setAttribute("editError", "Empty name is not allowed");
        }

        boolean success = categoryService.updateCategoryById(id, name);
        if (!success) {
            req.setAttribute("editError", "Error while creating category");
        }

        resp.sendRedirect(CategoryListServlet.URL);

    }
}
