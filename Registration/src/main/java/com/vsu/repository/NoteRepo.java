package com.vsu.repository;

import com.vsu.entity.Note;
import com.vsu.exception.DBException;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NoteRepo {
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM note WHERE id_note = ?";
    private static final String SELECT_ALL_BY_USER_ID_QUERY = "SELECT * FROM note WHERE id_profile = ?";
    private static final String INSERT_QUERY = "INSERT INTO note (text_note, id_profile) VALUES (?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM note WHERE id_note = ?";
    private static final String UPDATE_QUERY = "UPDATE note SET text_note=?, id_profile=? " + "WHERE id_note = ?";
    private final ConnectionFactory connectionFactory;

    public NoteRepo(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Note selectById(Long id) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getNote(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<Note> selectAllByUserId(Long idUser) {
        try (Connection connection = connectionFactory.getConnection()) {
            List<Note> noteList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_USER_ID_QUERY);
            statement.setLong(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                noteList.add(getNote(resultSet));
            }
            return noteList;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public int insert(Note note) {
        int countUpdate;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            setNoteParamsToStatement(note, statement);
            countUpdate = statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    note.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new DBException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    public int deleteById(Long id) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, id);
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    public int updateByID(Note note) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            setNoteParamsToStatement(note, statement);
            statement.setLong(3, note.getId());
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    private void setNoteParamsToStatement(Note note, PreparedStatement statement) throws SQLException {
        statement.setString(1, note.getText());
        statement.setLong(2, note.getIdUser());
    }

    @NotNull
    private Note getNote(ResultSet resultSet) throws SQLException {
        Note note = new Note();
        note.setId(resultSet.getLong("id_note"));
        note.setText(resultSet.getString("text_note"));
        note.setTimeCreation(resultSet.getTimestamp("time_creation").toString());
        note.setIdUser(resultSet.getLong("id_profile"));
        return note;
    }
}
