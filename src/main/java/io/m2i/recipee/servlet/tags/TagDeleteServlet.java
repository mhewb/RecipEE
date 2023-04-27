package io.m2i.recipee.servlet.tags;

import io.m2i.recipee.service.TagService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = TagDeleteServlet.URL)
public class TagDeleteServlet extends HttpServlet {

    public static final String URL = "/delete-tag";
//    private static final String JSP = "";
    private TagService tagService = new TagService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        tagService.deleteTagById(id);

        req.getRequestDispatcher(TagListServlet.URL).forward(req, resp);

    }
    

}
