package io.m2i.recipee.servlet.users;

import io.m2i.recipee.model.User;
import io.m2i.recipee.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = UserEditServlet.URL)
public class UserEditServlet extends HttpServlet {

    public static final String URL = "/edit-user";
    private static final String JSP = "/WEB-INF/users/user-form.jsp";
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        User user = userService.getUserById(id);

        req.setAttribute("user", user);
        req.setAttribute("isEdit", "isEdit");

        req.getRequestDispatcher(UserEditServlet.JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String avatarUrl = req.getParameter("avatarUrl");

        if ( email.isBlank() || password.isBlank() ) {
            req.setAttribute("editError", "Empty email or password is not allowed");
        }

        boolean success = userService.updateUserById(id, email, password, firstName, lastName, avatarUrl);
        if (!success) {
            req.setAttribute("editError", "Error while creating user");
        }

        resp.sendRedirect(UserListServlet.URL);

    }
}
