package com.example.cafe.Controller;

import com.example.cafe.DTO.ReservationDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute("reservation")
    public ReservationDTO reservation() {
        return new ReservationDTO();
    }
}
