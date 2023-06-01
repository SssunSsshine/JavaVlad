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
import java.util.List;

@WebServlet("/note/show")
public class ShowAllNotes extends HttpServlet {
    public static final String JSP_PATH = "/jsp/";
    private NoteService noteService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        noteService = new NoteService(new NoteRepo(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Note> noteList = noteService.selectAllByUserId(user.getId());
        RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "notes.jsp");;
        req.setAttribute("notes", noteList);
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
