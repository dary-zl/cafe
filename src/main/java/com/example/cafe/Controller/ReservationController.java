package com.example.cafe.Controller;

import com.example.cafe.DTO.ReservationDTO;
import com.example.cafe.Service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping()
    public String userList(Model model) {
        model.addAttribute("reservations", reservationService.getAll());
        return "reservations";
    }

    @PostMapping("/new")
    public String saveReservation(@ModelAttribute("reservation") ReservationDTO reservationDTO,
                                  RedirectAttributes redirectAttributes) {
        if(reservationService.save(reservationDTO)) {
            redirectAttributes.addFlashAttribute("success", "Столик успешно забронирован!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Ошибка при бронировании");
            redirectAttributes.addFlashAttribute("reservation", reservationDTO);
        }
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteReservation(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            reservationService.deleteReservation(id);
            //redirectAttributes.addFlashAttribute("success", "Бронирование успешно удалено!");
        } catch (Exception e) {
            //redirectAttributes.addFlashAttribute("error", "Ошибка при удалении бронирования");
        }
        return "redirect:/reservations";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ReservationDTO reservation = reservationService.findById(id);
        model.addAttribute("reservation", reservation);
        return "edit-reservation";
    }

    @PostMapping("/update/{id}")
    public String updateReservation(@PathVariable Long id,
                                    @ModelAttribute("reservation") ReservationDTO reservationDTO,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return "edit-reservation";
        }
        reservationService.updateReservation(id, reservationDTO);
        return "redirect:/reservations";
    }


}
