package com.dnikitin.entity;

import com.dnikitin.entity.converter.BirthdayConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    //Minimal requirement for a JPA Entity: @Entity and @Id (Primary Key).
    //Primary Key must implement Serializable (String does it by default).

    @Id
    private String username;

    private String firstName;
    private String lastName;

    @Column(name="birth_date")
    @Convert(converter = BirthdayConverter.class)
    // Converts custom Java types to a basic JDBC-compatible type.
    // Can be applied globally via @Converter(autoApply = true)
    // or manually in Hibernate configuration.
    private Birthday birthDate;

    //Defines how Enum is persisted: as a String or an integer index (EnumType.ORDINAL).
    @Enumerated(EnumType.STRING)
    private Role role;


    // Informs Hibernate to treat the field as JSON on the JDBC level.
    @JdbcTypeCode(SqlTypes.JSON)
    // Forces the DDL to use PostgreSQL's binary JSON format (jsonb).
    @Column(columnDefinition = "jsonb")
    private String info;
}
