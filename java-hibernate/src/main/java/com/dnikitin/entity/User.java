package com.dnikitin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    //two main annotation for POJO to be Hibernate Entity - @Entuty, @Id
    //Id shoulb be Serializable

    @Id
    private String username;

    private String firstName;
    private String lastName;

    @Column(name="birth_date")
    private LocalDate birthDate;

    private Integer age;
}
