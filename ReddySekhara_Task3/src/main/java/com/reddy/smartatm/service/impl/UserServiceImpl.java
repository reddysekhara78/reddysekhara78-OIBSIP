package com.reddy.smartatm.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.reddy.smartatm.entity.User;
import com.reddy.smartatm.exception.InvalidCredentialsException;
import com.reddy.smartatm.exception.UserNotFoundException;
import com.reddy.smartatm.repository.UserRepository;
import com.reddy.smartatm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found."));
    }

    @Override
    public User login(String username, String password) {

        Optional<User> optionalUser =
                userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {

            throw new UserNotFoundException(
                    "Username does not exist.");
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(password)) {

            throw new InvalidCredentialsException(
                    "Invalid password.");
        }

        if (!user.getActive()) {

            throw new InvalidCredentialsException(
                    "User account is inactive.");
        }

        return user;
    }

    @Override
    public void changePassword(User user,
                               String currentPassword,
                               String newPassword) {

        if (!user.getPassword().equals(currentPassword)) {

            throw new InvalidCredentialsException(
                    "Current password is incorrect.");
        }

        user.setPassword(newPassword);

        userRepository.save(user);
    }

    @Override
    public User updateProfile(User user,
                              String email,
                              String phone) {

        user.setEmail(email);
        user.setPhone(phone);

        return userRepository.save(user);
    }

    // ===============================
    // Forgot Password
    // ===============================

    @Override
    public User findByUsernameAndEmail(String username,
                                       String email) {

        return userRepository
                .findByUsername(username)
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "Username and Email do not match."));
    }

    @Override
    public void resetPassword(User user,
                              String newPassword) {

        user.setPassword(newPassword);

        userRepository.save(user);
    }

}