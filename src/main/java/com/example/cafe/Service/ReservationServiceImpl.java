package com.example.cafe.Service;

import com.example.cafe.DAO.ReservationRepository;
import com.example.cafe.DTO.ReservationDTO;
import com.example.cafe.DataBase.Reservation;
import jakarta.persistence.EntityNotFoundException;
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

    private ReservationDTO toDto(Reservation reservation){
        return ReservationDTO.builder()
                .id(reservation.getId())
                .name(reservation.getName())
                .count(reservation.getCount())
                .phone((reservation.getPhone()))
                .reservationTime(reservation.getReservationTime())
                .build();
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public ReservationDTO findById(Long id) {
        return reservationRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
    }

    @Override
    public void updateReservation(Long id, ReservationDTO reservationDTO) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        reservation.setName(reservationDTO.getName());
        reservation.setPhone(reservationDTO.getPhone());
        reservation.setCount(reservationDTO.getCount());
        reservation.setReservationTime(reservationDTO.getReservationTime());

        reservationRepository.save(reservation);
    }


}
