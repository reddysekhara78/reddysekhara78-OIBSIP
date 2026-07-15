package com.reddy.onlinereservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reddy.onlinereservation.entity.Reservation;
import com.reddy.onlinereservation.entity.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

    List<Reservation> findByUser(User user);

    Reservation findByPnr(String pnr);
    

List<Reservation> findByUserAndPnrContaining(User user, String pnr);
long countByUser(User user);

long countByUserAndStatus(User user, String status);

}
