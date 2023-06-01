package com.vsu.servlet.note;

import com.vsu.entity.Note;
import com.vsu.repository.NoteRepo;
import com.vsu.repository.ConnectionFactory;
import com.vsu.service.NoteService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/note/edit")
public class EditNote extends HttpServlet {
    private static final String JSP_PATH = "/jsp/";
    private NoteService noteService;

    @Override
    public void init(ServletConfig config) {
        noteService = new NoteService(new NoteRepo(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        Note existingNote;
        try {
            existingNote = noteService.selectById(id);
            RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "note-form.jsp");
            req.setAttribute("note", existingNote);
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}