package com.vsu.service;

import com.vsu.entity.Note;
import com.vsu.repository.NoteRepo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoteService {
    private static final int MIN_COUNT_UPDATE = 1;
    private final NoteRepo noteRepo;
    private static final Logger LOGGER = Logger.getLogger(NoteService.class.getName());

    public NoteService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    public Note insertNote(Note note) {
        if (noteRepo.insert(note) < MIN_COUNT_UPDATE) {
            LOGGER.log(Level.INFO, "Note with id {0} is not inserted", note.getId());
            return null;
        } else {
            return note;
        }
    }

    public void deleteNote(Long id) {
        if (noteRepo.deleteById(id) < MIN_COUNT_UPDATE) {
            LOGGER.log(Level.INFO, "Note with id {0} is not deleted", id);
        }
    }

    public Note selectById(String id) {
        Long idL = Long.parseLong(id);
        return noteRepo.selectById(idL);
    }

    public List<Note> selectAllByUserId(Long id) {
        return noteRepo.selectAllByUserId(id);
    }

    public void updateByID(Note note) {
        if (noteRepo.updateByID(note) < MIN_COUNT_UPDATE) {
            LOGGER.log(Level.INFO, "Note with id {} is not updated", note.getId());
        }
    }
}
