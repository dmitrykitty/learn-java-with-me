package com.dnikitin.javaspringboottest2.repository;

import com.dnikitin.javaspringboottest2.controllers.ReservationController;
import com.dnikitin.javaspringboottest2.dto.ReservationStatus;
import com.dnikitin.javaspringboottest2.model.entity.ReservationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

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
    boolean checkCollision(
            @Param("roomId") Long roomId,
            @Param("reservationId") Long reservationId,
            @Param("endDate") LocalDate endDate,
            @Param("startDate") LocalDate startDate
    );

    @Modifying
    @Query("""
            UPDATE ReservationEntity r
            SET r.status = :status
            WHERE r.id = :reservationId
            """)
    void changeReservationStatus(
            @Param("reservationId") Long reservationId,
            @Param("status") ReservationStatus status);


    @Query("""
            SELECT r.id, r.roomId, r.userId, r.startDate, r.endDate, r.status 
            from ReservationEntity r
            WHERE (:userId IS NULL OR r.userId = :userId)
            AND (:roomId IS NULL OR r.roomId = :roomId)
            """)
    List<ReservationEntity> findReservationsByFilter(
            @Param("roomId") Long roomId,
            @Param("userId") Long userId,
            Pageable pageable
    );

}
