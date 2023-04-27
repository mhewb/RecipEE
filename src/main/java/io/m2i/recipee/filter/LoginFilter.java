package io.m2i.recipee.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoginFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        HttpSession session = httpServletRequest.getSession();

        boolean loggedIn = session != null && session.getAttribute("loggedUser") != null;

        // Public route accessible w/o connexion
        if (
                httpServletRequest.getRequestURI().equals(httpServletRequest.getContextPath() + "/")
                        || httpServletRequest.getRequestURI().equals(httpServletRequest.getContextPath() + "/login")
                        || httpServletRequest.getRequestURI().equals(httpServletRequest.getContextPath() + "/register")
                        || httpServletRequest.getRequestURI().equals(httpServletRequest.getContextPath() + "/search")
                        || httpServletRequest.getRequestURI().contains(httpServletRequest.getContextPath() + "/recipes-list")
                        || httpServletRequest.getRequestURI().contains(httpServletRequest.getContextPath() + "/recipe-details")

        ) {
            chain.doFilter(req, resp); // Les pages restent accessibles
        } else {
            if (!loggedIn) {
                // If no username: go to login page
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
            } else {
                // Continue la chaine des filtres/navigations
                chain.doFilter(req, resp);
            }
        }

    }
}
