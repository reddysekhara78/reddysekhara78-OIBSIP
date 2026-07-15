
package com.reddy.onlinereservation.controller;

import com.reddy.onlinereservation.entity.Reservation;
import com.reddy.onlinereservation.entity.User;
import com.reddy.onlinereservation.service.ReservationService;
import com.reddy.onlinereservation.service.TrainService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TrainService trainService;

    // ==========================
    // Book Ticket Page
    // ==========================
    @GetMapping("/ticket/{id}")
public String viewTicket(@PathVariable Long id,
                         HttpSession session,
                         Model model) {

    User user = (User) session.getAttribute("loggedUser");

    if (user == null) {
        return "redirect:/";
    }

    Reservation reservation = reservationService.getReservation(id);

    if (reservation == null || !reservation.getUser().getId().equals(user.getId())) {
        return "redirect:/reservations";
    }

    model.addAttribute("reservation", reservation);

    return "ticket";
}
    @GetMapping("/book")
    public String bookingPage(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("reservation", new Reservation());

        // Send all trains to the booking page
        model.addAttribute("trains", trainService.getAllTrains());

        return "book-ticket";
    }

    // ==========================
    // Save Reservation
    // ==========================
    @PostMapping("/book")
    public String saveReservation(@ModelAttribute Reservation reservation,
                                  HttpSession session,
                                  Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/";
        }

        reservation.setUser(user);

        Reservation saved = reservationService.saveReservation(reservation);

        model.addAttribute("reservation", saved);

        return "ticket";
    }

    // ==========================
    // My Reservations
    // ==========================
    @GetMapping("/reservations")
    public String myReservations(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("reservations",
                reservationService.getReservations(user));

        return "my-reservations";
    }

    // ==========================
    // Search Reservation
    // ==========================
    @GetMapping("/search")
    public String search(@RequestParam String pnr,
                         HttpSession session,
                         Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("reservations",
                reservationService.searchReservation(user, pnr));

        return "my-reservations";
    }

    // ==========================
    // Cancel Reservation
    // ==========================
    @GetMapping("/cancel/{id}")
    public String cancel(@PathVariable Long id) {

        Reservation reservation = reservationService.getReservation(id);

        if (reservation != null) {
            reservation.setStatus("CANCELLED");
            reservationService.save(reservation);
        }

        return "redirect:/reservations";
    }

    // ==========================
    // Edit Reservation Page
    // ==========================
    @GetMapping("/edit/{id}")
    public String editReservation(@PathVariable Long id,
                                  HttpSession session,
                                  Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/";
        }

        Reservation reservation = reservationService.getReservation(id);

        if (reservation == null || !reservation.getUser().getId().equals(user.getId())) {
            return "redirect:/reservations";
        }

        model.addAttribute("reservation", reservation);

        // Send train list for dropdown
        model.addAttribute("trains", trainService.getAllTrains());

        return "edit-reservation";
    }

    // ==========================
    // Update Reservation
    // ==========================
    @PostMapping("/update")
    public String updateReservation(@ModelAttribute Reservation reservation,
                                    HttpSession session) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/";
        }

        Reservation existing = reservationService.getReservation(reservation.getId());

        if (existing == null || !existing.getUser().getId().equals(user.getId())) {
            return "redirect:/reservations";
        }

        existing.setPassengerName(reservation.getPassengerName());
        existing.setAge(reservation.getAge());
        existing.setGender(reservation.getGender());
        existing.setMobile(reservation.getMobile());
        existing.setEmail(reservation.getEmail());
        existing.setSource(reservation.getSource());
        existing.setDestination(reservation.getDestination());
        existing.setJourneyDate(reservation.getJourneyDate());
        existing.setTrainNumber(reservation.getTrainNumber());
        existing.setTrainName(reservation.getTrainName());
        existing.setTravelClass(reservation.getTravelClass());
        existing.setSeats(reservation.getSeats());

        reservationService.save(existing);

        return "redirect:/reservations";
    }

}

