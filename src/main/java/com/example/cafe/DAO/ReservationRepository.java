package com.example.cafe.DAO;

import com.example.cafe.DataBase.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findByName(String name);
}
