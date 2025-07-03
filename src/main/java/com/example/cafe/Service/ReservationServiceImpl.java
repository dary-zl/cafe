package com.example.cafe.Service;

import com.example.cafe.DAO.ReservationRepository;
import com.example.cafe.DTO.ReservationDTO;
import com.example.cafe.DataBase.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @Override
    public boolean save(ReservationDTO reservationDTO) {
        System.out.println("=== Начало save(ReservationDTO) ===");
        Reservation reservation = Reservation.builder()
                        .name(reservationDTO.getName())
                        .count(reservationDTO.getCount()).phone(reservationDTO.getPhone())
                        .reservationTime(reservationDTO.getReservationTime()).
                        build();

        this.save(reservation);
        System.out.println("Reservation saved successfully: " + reservationDTO.getName());
        return true;
    }

    @Override
    public void save(Reservation reservation) {
        System.out.println("=== Начало save(Reservation) ===");
        reservationRepository.save(reservation);
    }

    @Override
    public List<ReservationDTO> getAll() {
        return reservationRepository.findAll().stream()
                .map(this::toDto)
                .collect((Collectors.toList()));
    }

    @Override
    public Reservation findByName(String name) {
        return reservationRepository.findByName(name);
    }

    private ReservationDTO toDto(Reservation reservation){
        return ReservationDTO.builder()
                .name(reservation.getName())
                .count(reservation.getCount())
                .phone((reservation.getPhone()))
                .reservationTime(reservation.getReservationTime())
                .build();
    }
}
