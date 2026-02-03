package com.dnikitin.javaspringboottest2.repository;

import com.dnikitin.javaspringboottest2.controllers.ReservationController;
import com.dnikitin.javaspringboottest2.dto.ReservationStatus;
import com.dnikitin.javaspringboottest2.model.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {


    @Query(value = """
            SELECT COUNT(r) > 0
            FROM reservations r
            WHERE r.room_id = :roomId
                AND r.status = 'APPROVED'
                AND r.id != :reservationId
                AND r.start_date < :endDate
                AND r.end_date > :startDate
            """, nativeQuery = true)
    boolean checkCollision(@Param("roomId") Long roomId,
                           @Param("reservationId") Long reservationId,
                           @Param("endDate") LocalDate endDate,
                           @Param("startDate") LocalDate startDate
    );

    @Modifying
    @Query(value = """
            UPDATE reservations
            SET status = :status
            WHERE id = :reservationId
            """, nativeQuery = true)
    void changeReservationStatus(
            @Param("reservationId") Long reservationId,
            @Param("status") ReservationStatus status);

}
