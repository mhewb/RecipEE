package io.m2i.recipee.servlet.users;

import io.m2i.recipee.model.User;
import io.m2i.recipee.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = UserListServlet.URL)
public class UserListServlet extends HttpServlet {

    public static final String URL = "/users-list";
    private static final String JSP = "/WEB-INF/users/users-list.jsp";
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> userList = userService.findAllUsers();
        req.setAttribute("users", userList);

        req.getRequestDispatcher(JSP).forward(req, resp);

    }
}
