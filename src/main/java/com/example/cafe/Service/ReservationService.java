package com.example.cafe.Service;

import com.example.cafe.DTO.ReservationDTO;
import com.example.cafe.DataBase.Reservation;

import java.util.List;

public interface ReservationService {
    boolean save(ReservationDTO reservationDTO);
    void save(Reservation reservation);
    List<ReservationDTO> getAll();
    Reservation findByName(String name);
}
