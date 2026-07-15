package com.reddy.onlinereservation.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reddy.onlinereservation.entity.Reservation;
import com.reddy.onlinereservation.entity.User;
import com.reddy.onlinereservation.repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    public Reservation saveReservation(Reservation reservation){

        reservation.setPnr(generatePNR());

        reservation.setBookingDate(LocalDateTime.now());

        reservation.setStatus("BOOKED");

        return repository.save(reservation);
    }

    private String generatePNR(){

        Random random=new Random();

        return "PNR"+(100000+random.nextInt(900000));

    }
    public List<Reservation> getReservations(User user){
    return repository.findByUser(user);
}

public List<Reservation> searchReservation(User user,String pnr){
    return repository.findByUserAndPnrContaining(user,pnr);
}

public Reservation getReservation(Long id){
    return repository.findById(id).orElse(null);
}

public void save(Reservation reservation){
    repository.save(reservation);
}
public long totalBookings(User user){

    return repository.countByUser(user);

}

public long bookedTickets(User user){

    return repository.countByUserAndStatus(user,"BOOKED");

}

public long cancelledTickets(User user){

    return repository.countByUserAndStatus(user,"CANCELLED");

}
}