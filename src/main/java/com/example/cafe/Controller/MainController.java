package com.example.cafe.Controller;

import com.example.cafe.DTO.ReservationDTO;
import com.example.cafe.DataBase.Reservation;
import com.example.cafe.Service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MainController {
    private final ReservationService reservationService;

    public MainController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }
}
