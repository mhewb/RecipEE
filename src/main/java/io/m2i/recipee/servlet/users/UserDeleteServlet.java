package io.m2i.recipee.servlet.users;

import io.m2i.recipee.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = UserDeleteServlet.URL)
public class UserDeleteServlet extends HttpServlet {

    public static final String URL = "/delete-user";
//    private static final String JSP = "";
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        userService.deleteUserById(id);

        req.getRequestDispatcher(UserListServlet.URL).forward(req, resp);

    }
    

}
