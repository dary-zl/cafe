package com.example.cafe.DAO;

import com.example.cafe.DataBase.Reservation;
import com.example.cafe.DataBase.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findByName(String name);
}
