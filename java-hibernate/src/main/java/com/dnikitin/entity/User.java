package com.dnikitin.entity;

import com.dnikitin.entity.converter.BirthdayConverter;
import jakarta.persistence.*;
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
    @Convert(converter = BirthdayConverter.class)
    //allow to convert custom user fileds to SQL types
    //also possible to set in the configurationj (config.addAttributeConverter(new BirthdayConverter(), true)
    //if we want to use conversion without annotations in all tables with the same field
    private Birthday birthDate;

    //annotation to convert enumtype to string or in (EnumType.INT)
    @Enumerated(EnumType.STRING)
    private Role role;
}
