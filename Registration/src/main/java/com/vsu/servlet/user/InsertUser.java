package com.vsu.servlet.user;

import com.vsu.entity.User;
import com.vsu.repository.ConnectionFactory;
import com.vsu.repository.UserRepo;
import com.vsu.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/insert")
public class InsertUser extends HttpServlet {
    private static final String JSP_PATH = "/jsp/";
    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = new UserService(new UserRepo(new ConnectionFactory()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String birthday = req.getParameter("birthday");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        User user;
        try {
            user = userService.insertUser(new User(surname, name, birthday, email, phone, password));
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/new");
            req.setAttribute("user", new User(surname, name, birthday, email, phone, password));
            req.setAttribute("isInsert",true);
            req.setAttribute("error", e.toString());
            dispatcher.forward(req, resp);
            return;
        }
        session.setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() +"/user/page");
    }
}
