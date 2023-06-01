package com.vsu.repository;

import com.vsu.entity.User;
import com.vsu.exception.DBException;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM profile WHERE id_profile = ?";
    private static final String SELECT_BY_EMAIL_QUERY = "SELECT * FROM profile WHERE email_profile = ?";
    private static final String INSERT_QUERY = "INSERT INTO profile(" + "surname_profile, name_profile, birthday_profile, phone_profile, email_profile, password_profile) " + "VALUES (?, ?, ?, ?, ?, ?);";
    private static final String DELETE_QUERY_BY_ID = "DELETE FROM profile WHERE id_profile = ?";
    private static final String UPDATE_QUERY = "UPDATE profile " + "SET surname_profile=?, name_profile=?, birthday_profile=?, phone_profile=?, email_profile=?, password_profile=? " + "WHERE id_profile = ?";
    private final ConnectionFactory connectionFactory;

    public UserRepo(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public User selectById(Long id) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUser(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public User selectByEmail(String email) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL_QUERY);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUser(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public int insert(User user) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            setUserParamsToStatement(user, statement);
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }


    public int deleteById(Long id) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY_BY_ID);
            statement.setLong(1, id);
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    public int updateByID(User user) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            setUserParamsToStatement(user, statement);
            statement.setLong(7, user.getId());
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    private void setUserParamsToStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getSurname());
        statement.setString(2, user.getName());
        statement.setDate(3, Date.valueOf(user.getBirthday()));
        statement.setString(4, user.getPhone());
        statement.setString(5, user.getEmail());
        statement.setString(6, user.getPassword());
    }

    @NotNull
    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id_profile"));
        user.setName(resultSet.getString("name_profile"));
        user.setSurname(resultSet.getString("surname_profile"));
        user.setBirthday((resultSet.getDate("birthday_profile")).toString());
        user.setEmail(resultSet.getString("email_profile"));
        user.setPhone(resultSet.getString("phone_profile"));
        user.setPassword(resultSet.getString("password_profile"));
        return user;
    }
}

