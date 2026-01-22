package com.dnikitin.entity.embeddedcomponent;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//annotatuion to map whole class to separate columns in DB
@Embeddable
public class PersonalInfo{
    private String name;
    private String surname;
    private String phoneNumber;
}
