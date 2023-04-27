package io.m2i.recipee.servlet.tags;

import io.m2i.recipee.dao.TagDAO;
import io.m2i.recipee.model.Tag;
import io.m2i.recipee.service.TagService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = TagCreateServlet.URL)
public class TagCreateServlet extends HttpServlet {

    public static final String URL = "/create-tag";
    private static final String JSP = "/WEB-INF/tags/tag-form.jsp";
    private TagService tagService = new TagService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(TagCreateServlet.JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("tagName");

        if ( name.isBlank() ) {
            req.setAttribute("createError", "Empty name is not allowed");
        }

        Tag tag = tagService.createTag(name);
        if (tag == null) {
            req.setAttribute("createError", "Error while creating tag");
        }

        req.getRequestDispatcher(TagCreateServlet.JSP).forward(req, resp);

    }
}
