package io.m2i.recipee.servlet.categories;

import io.m2i.recipee.model.Category;
import io.m2i.recipee.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = CategoryListServlet.URL)
public class CategoryListServlet extends HttpServlet {

    public static final String URL = "/categories-list";
    private static final String JSP = "/WEB-INF/categories/categories-list.jsp";
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Category> categoryList = categoryService.findAllCategories();
        req.setAttribute("categories", categoryList);

        req.getRequestDispatcher(JSP).forward(req, resp);

    }
}
