package com.example.cafe.Controller;

import com.example.cafe.DTO.ReservationDTO;
import com.example.cafe.Service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    private final ReservationService reservationService;

    public MainController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping({"", "/", "/index"})
    public String index(Model model) {
        model.addAttribute("reservation", new ReservationDTO());
        return "index";
    }

    @PostMapping("/reservations/new")
    public String saveReservation(@ModelAttribute("reservation") ReservationDTO reservationDTO,
                                  Model model) {
        if(reservationService.save(reservationDTO)) {
            model.addAttribute("success", "Столик успешно забронирован!");
            model.addAttribute("reservation", new ReservationDTO());
            return "index";
        } else {
            model.addAttribute("error", "Ошибка при бронировании");
            model.addAttribute("reservation", reservationDTO);
            return "index";
        }
    }
}
