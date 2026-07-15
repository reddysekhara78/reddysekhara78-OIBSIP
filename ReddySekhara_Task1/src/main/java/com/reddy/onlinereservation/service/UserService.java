package com.reddy.onlinereservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reddy.onlinereservation.entity.User;
import com.reddy.onlinereservation.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User register(User user) {
        return repository.save(user);
    }

    public User login(String email, String password) {

        User user = repository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
    public User getUser(Long id) {
    return repository.findById(id).orElse(null);
}

public User updateUser(User user) {
    return repository.save(user);
}
public boolean changePassword(User user, String oldPassword, String newPassword) {

    if (!user.getPassword().equals(oldPassword)) {
        return false;
    }

    user.setPassword(newPassword);
    repository.save(user);

    return true;
}
public boolean resetPassword(String email,
                             String password){

    User user=repository.findByEmail(email);

    if(user==null){

        return false;

    }

    user.setPassword(password);

    repository.save(user);

    return true;

}
}