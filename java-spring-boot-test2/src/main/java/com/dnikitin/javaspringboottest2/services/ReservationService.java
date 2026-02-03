package com.dnikitin.javaspringboottest2.services;

import com.dnikitin.javaspringboottest2.dto.Reservation;
import com.dnikitin.javaspringboottest2.dto.ReservationStatus;
import com.dnikitin.javaspringboottest2.model.entity.ReservationEntity;
import com.dnikitin.javaspringboottest2.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public Reservation findReservationById(Long id) {
        ReservationEntity reservationEntity = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Reservation not found with id " + id)
        );
        return mapFromEntity(reservationEntity);
    }

    public List<Reservation> findAllReservations() {
        return repository.findAll().stream()
                .map(this::mapFromEntity)
                .toList();
    }

    public Reservation saveReservation(Reservation reservation) {
        if (reservation.id() != null) {
            throw new IllegalArgumentException("Reservation id should be null");
        }
        ReservationEntity reservationEntity = mapToEntity(reservation);
        ReservationEntity saved = repository.save(reservationEntity);
        return mapFromEntity(saved);
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        ReservationEntity reservationEntity = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Reservation not found with id " + id)
        );
        if(reservationEntity.getStatus() != ReservationStatus.PENDING) {
            throw new IllegalStateException("Cannot  modify reservation with status: " + reservationEntity.getStatus());
        }
        ReservationEntity updated = repository.save(ReservationEntity.builder()
                .id(reservationEntity.getId())
                .userId(reservation.userId())
                .roomId(reservation.roomId())
                .startDate(reservation.startDate())
                .endDate(reservation.endDate())
                .status(reservation.status())
                .build());

        return mapFromEntity(updated);
    }
    @Transactional
    public void cancelReservation(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Reservation not found with id " + id);
        }
        repository.changeReservationStatus(id, ReservationStatus.CANCELLED);
    }

    @Transactional
    public Reservation approveReservation(Long id) {

        ReservationEntity reservationEntity = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Reservation not found with id " + id)
        );

        if(reservationEntity.getStatus() != ReservationStatus.PENDING) {
            throw new IllegalStateException("Cannot  modify reservation with status: " + reservationEntity.getStatus());
        }

        if(isConflict(reservationEntity)){
            throw new IllegalStateException("Reservation with id " + id + " is in conflict");
        }

        //dirty checking
        reservationEntity.setStatus(ReservationStatus.APPROVED);
        return  mapFromEntity(reservationEntity);
    }

    private boolean isConflict(ReservationEntity reservationEntity) {
        return repository.checkCollision(
                reservationEntity.getRoomId(),
                reservationEntity.getId(),
                reservationEntity.getEndDate(),
                reservationEntity.getStartDate());

    }

    private Reservation mapFromEntity(ReservationEntity reservationEntity) {
        return Reservation.builder()
                .id(reservationEntity.getId())
                .userId(reservationEntity.getUserId())
                .roomId(reservationEntity.getRoomId())
                .startDate(reservationEntity.getStartDate())
                .endDate(reservationEntity.getEndDate())
                .status(reservationEntity.getStatus())
                .build();
    }

    private ReservationEntity mapToEntity(Reservation reservation) {
        return ReservationEntity.builder()
                .id(reservation.id())
                .userId(reservation.userId())
                .roomId(reservation.roomId())
                .startDate(reservation.startDate())
                .endDate(reservation.endDate())
                .status(reservation.status())
                .build();
    }
}
