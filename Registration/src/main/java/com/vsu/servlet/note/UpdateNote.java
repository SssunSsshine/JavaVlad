package com.vsu.servlet.note;

import com.vsu.entity.Note;
import com.vsu.entity.User;
import com.vsu.repository.NoteRepo;
import com.vsu.repository.ConnectionFactory;
import com.vsu.service.NoteService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/note/update")
public class UpdateNote extends HttpServlet {
    private static final String JSP_PATH = "/jsp/";
    private NoteService noteService;

    @Override
    public void init(ServletConfig config) {
        noteService = new NoteService(new NoteRepo(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String text = req.getParameter("text");
        String timeCreation = req.getParameter("timeCreation");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Note note = new Note(id, text, timeCreation, user.getId());

        try {
            noteService.updateByID(note);
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "note-form.jsp");
            req.setAttribute("error", e.toString());
            dispatcher.forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/note/show");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

