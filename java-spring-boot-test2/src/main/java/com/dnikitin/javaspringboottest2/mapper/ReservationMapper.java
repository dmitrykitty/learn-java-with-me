package com.dnikitin.javaspringboottest2.mapper;

import com.dnikitin.javaspringboottest2.dto.Reservation;
import com.dnikitin.javaspringboottest2.model.entity.ReservationEntity;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public Reservation mapFromEntity(ReservationEntity reservationEntity) {
        return Reservation.builder()
                .id(reservationEntity.getId())
                .userId(reservationEntity.getUserId())
                .roomId(reservationEntity.getRoomId())
                .startDate(reservationEntity.getStartDate())
                .endDate(reservationEntity.getEndDate())
                .status(reservationEntity.getStatus())
                .build();
    }

    public ReservationEntity mapToEntity(Reservation reservation) {
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
