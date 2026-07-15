package com.reddy.onlinereservation.controller;

import com.reddy.onlinereservation.entity.User;
import com.reddy.onlinereservation.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model){

        User user=(User)session.getAttribute("loggedUser");

        if(user==null){
            return "redirect:/";
        }

        model.addAttribute("user",user);

        model.addAttribute("totalBookings",
                reservationService.totalBookings(user));

        model.addAttribute("bookedTickets",
                reservationService.bookedTickets(user));

        model.addAttribute("cancelledTickets",
                reservationService.cancelledTickets(user));

        return "dashboard";

    }

}