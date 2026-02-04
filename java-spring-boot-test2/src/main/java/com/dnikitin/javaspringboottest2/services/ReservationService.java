package com.dnikitin.javaspringboottest2.services;

import com.dnikitin.javaspringboottest2.dto.Reservation;
import com.dnikitin.javaspringboottest2.dto.ReservationStatus;
import com.dnikitin.javaspringboottest2.mapper.ReservationMapper;
import com.dnikitin.javaspringboottest2.model.entity.ReservationEntity;
import com.dnikitin.javaspringboottest2.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository repository;
    private final ReservationMapper mapper;

    public ReservationService(ReservationRepository repository,
                              ReservationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Reservation findReservationById(Long id) {
        ReservationEntity reservationEntity = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Reservation not found with id " + id)
        );
        return mapper.mapFromEntity(reservationEntity);
    }

    public List<Reservation> findAllByFilter(ReservationFilter filter) {
        int pageSize = filter.pageSize() == null ? 10 : filter.pageSize();
        int pageNumber = filter.pageNumber() == null ? 0 : filter.pageNumber();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return repository.findReservationsByFilter(
                        filter.roomId(),
                        filter.userId(),
                        pageable)
                .stream()
                .map(mapper::mapFromEntity)
                .toList();
    }

    public Reservation saveReservation(Reservation reservation) {
        if (reservation.status() != null) {
            throw new IllegalArgumentException("Status should be null");
        }
        if (!reservation.startDate().isBefore(reservation.endDate())) {
            throw new IllegalArgumentException("Start date should be at least 1 day before end date");
        }
        ReservationEntity reservationEntity = mapper.mapToEntity(reservation);
        reservationEntity.setStatus(ReservationStatus.PENDING);
        ReservationEntity saved = repository.save(reservationEntity);
        return mapper.mapFromEntity(saved);
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        ReservationEntity reservationEntity = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Reservation not found with id " + id)
        );
        if (reservationEntity.getStatus() != ReservationStatus.PENDING) {
            throw new IllegalStateException("Cannot  modify reservation with status: " + reservationEntity.getStatus());
        }
        if (!reservation.startDate().isBefore(reservation.endDate())) {
            throw new IllegalArgumentException("Start date should be at least 1 day before end date");
        }

        ReservationEntity entityToUpdate = mapper.mapToEntity(reservation);
        entityToUpdate.setId(reservationEntity.getId());
        ReservationEntity updated = repository.save(entityToUpdate);
        return mapper.mapFromEntity(updated);
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

        if (reservationEntity.getStatus() != ReservationStatus.PENDING) {
            throw new IllegalStateException("Cannot  modify reservation with status: " + reservationEntity.getStatus());
        }

        if (isConflict(reservationEntity)) {
            throw new IllegalStateException("Reservation with id " + id + " is in conflict");
        }

        //dirty checking
        reservationEntity.setStatus(ReservationStatus.APPROVED);
        return mapper.mapFromEntity(reservationEntity);
    }

    private boolean isConflict(ReservationEntity reservationEntity) {
        return repository.checkCollision(
                reservationEntity.getRoomId(),
                reservationEntity.getId(),
                reservationEntity.getEndDate(),
                reservationEntity.getStartDate());

    }
}
