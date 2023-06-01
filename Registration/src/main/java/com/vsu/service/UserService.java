package com.vsu.service;

import com.vsu.entity.User;
import com.vsu.exception.ValidationException;
import com.vsu.repository.UserRepo;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {
    private static final int MIN_COUNT_UPDATE = 1;
    private final UserRepo userRepo;
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getByLoginAndPassword(String login, String password) {
        User user = userRepo.selectByEmail(login);
        if (user == null) {
            LOGGER.log(Level.INFO, "User with login {0} is not exist", login);
            throw new ValidationException("User is not found");
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        LOGGER.log(Level.WARNING, "Something wrong with input data");
        throw new ValidationException("Bad credentials");
    }

    public User insertUser(User user) {
        validateUser(user);
        if (userRepo.insert(user) < MIN_COUNT_UPDATE) {
            LOGGER.log(Level.INFO, "User with id {0} is not inserted", user.getId());
            return null;
        } else {
            return userRepo.selectByEmail(user.getEmail());
        }
    }

    public void deleteUser(Long id) {
        if (userRepo.deleteById(id) < MIN_COUNT_UPDATE) {
            LOGGER.log(Level.INFO, "User with id {0} is not deleted", id);
        }
    }

    public User selectByIdUser(String id) {
        try {
            Long idL = Long.parseLong(id);
            return userRepo.selectById(idL);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "ID {0} has wrong type", id);
            throw new ValidationException("Bad ID");
        }

    }

    public void updateByID(User user) {
        validateUser(user);
        if (userRepo.updateByID(user) < MIN_COUNT_UPDATE) {
            LOGGER.log(Level.INFO, "User with id {} is not updated", user.getId());
        }
    }

    private static void validateUser(User user) {
        validateDate(user);
        validateName(user);
        validatePhone(user);
        validateEmail(user);
    }

    private static void validateEmail(User user) {
        String regexEmail = "^(.+)@(\\S+)$";
        if (!user.getEmail().matches(regexEmail)) {
            throw new ValidationException("Email format is invalid");
        }
    }

    private static void validatePhone(User user) {
        String regexPhone = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
        if (!user.getPhone().matches(regexPhone)) {
            throw new ValidationException("Phone format is invalid");
        }
    }

    private static void validateName(User user) {
        String regexName = "^([а-яА-ЯёЁ]+|[a-zA-Z]){1,50}";
        if (!user.getName().matches(regexName)) {
            throw new ValidationException("Bad name");
        }
        if (!user.getSurname().matches(regexName)) {
            throw new ValidationException("Bad surname");
        }
    }

    private static void validateDate(User user) {
        if (LocalDate.parse(user.getBirthday()).compareTo(LocalDate.now()) > 0) {
            throw new ValidationException("Bad date");
        }
    }
}
