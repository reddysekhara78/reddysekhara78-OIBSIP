package com.reddy.onlinereservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reddy.onlinereservation.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}