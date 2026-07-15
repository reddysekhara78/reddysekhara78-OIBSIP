package com.reddy.onlinereservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reddy.onlinereservation.entity.Train;
import com.reddy.onlinereservation.repository.TrainRepository;

@Service
public class TrainService {

    @Autowired
    private TrainRepository repository;

    public List<Train> getAllTrains() {

        return repository.findAll();

    }

}