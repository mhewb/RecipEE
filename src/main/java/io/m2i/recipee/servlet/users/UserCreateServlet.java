package io.m2i.recipee.servlet.users;

import io.m2i.recipee.model.User;
import io.m2i.recipee.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = UserCreateServlet.URL)
public class UserCreateServlet extends HttpServlet {

    public static final String URL = "/register";
    private static final String JSP = "/WEB-INF/users/user-form.jsp";
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(UserCreateServlet.JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String avatarUrl = req.getParameter("avatarUrl");

        if ( email.isBlank() || password.isBlank() ) {
            req.setAttribute("createError", "Empty email or password is not allowed");
        }

        boolean success = userService.createUser(email, password, firstName, lastName, avatarUrl);
        if (!success) {
            req.setAttribute("createError", "Error while creating user");
        }

        resp.sendRedirect(UserListServlet.URL);
    }
}
