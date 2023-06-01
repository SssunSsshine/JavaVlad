package com.vsu.filter;

import com.vsu.entity.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/", "/note/*", "/user/*"})
public class LoggedFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String loginURI = httpServletRequest.getContextPath() + "/user/login";
        boolean loginRequest = httpServletRequest.getRequestURI().equals(loginURI);
        User user = (User) session.getAttribute("user");
        if (loginRequest && user != null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user/page");
            return;
        } else if (!loginRequest && user == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user/login");
            return;
        }
        chain.doFilter(request, response);
    }
}
