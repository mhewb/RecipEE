package io.m2i.recipee.servlet.users;

import io.m2i.recipee.model.User;
import io.m2i.recipee.service.UserService;
import io.m2i.recipee.servlet.LandingPageServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = LoginServlet.URL)
public class LoginServlet extends HttpServlet {

    public static final String URL = "/login";
    public static final String JSP = "/WEB-INF/users/login.jsp";

    private UserService userService = new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(LoginServlet.JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean isLogged = userService.checkLog(email, password);

        if (isLogged == false) {
            req.setAttribute("loggingError", true);
            req.getRequestDispatcher(LoginServlet.JSP).forward(req, resp);
        } else {
            User user = userService.getUserByEmail(email);
            req.getSession().setAttribute("loggedUser", user);
            resp.sendRedirect(LandingPageServlet.URL);
        }

    }
}
