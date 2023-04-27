package io.m2i.recipee.servlet.tags;

import io.m2i.recipee.model.Tag;
import io.m2i.recipee.service.TagService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = TagEditServlet.URL)
public class TagEditServlet extends HttpServlet {

    public static final String URL = "/edit-tag";
    private static final String JSP = "/WEB-INF/tags/tag-form.jsp";
    private TagService tagService = new TagService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        Tag tag = tagService.getTagById(id);

        req.setAttribute("tag", tag);
        req.setAttribute("update", "update");

        req.getRequestDispatcher(TagEditServlet.JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("tagName");

        if ( name.isBlank() ) {
            req.setAttribute("editError", "Empty name is not allowed");
        }

        boolean success = tagService.updateTagById(id, name);
        if (!success) {
            req.setAttribute("editError", "Error while creating tag");
        }

        resp.sendRedirect(TagListServlet.URL);

    }
}
