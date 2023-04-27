package io.m2i.recipee.servlet.tags;

import io.m2i.recipee.model.Tag;
import io.m2i.recipee.service.TagService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = TagListServlet.URL)
public class TagListServlet extends HttpServlet {

    public static final String URL = "/tags-list";
    private static final String JSP = "/WEB-INF/tags/tags-list.jsp";
    private TagService tagService = new TagService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Tag> tagList = tagService.findAllTags();
        req.setAttribute("tags", tagList);

        req.getRequestDispatcher(JSP).forward(req, resp);

    }
}
