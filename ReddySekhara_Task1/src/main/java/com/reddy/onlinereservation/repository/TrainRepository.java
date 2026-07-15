package com.reddy.onlinereservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reddy.onlinereservation.entity.Train;

public interface TrainRepository extends JpaRepository<Train, Long> {

}