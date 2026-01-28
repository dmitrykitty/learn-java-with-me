package com.dnikitin.entity.manytoone_onetomany;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false,  fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    //LAZY - no company will be provided to the worker -> only proxy company
    //when we really wants to get company -> makes new select just to get only company

    //CascadeType - save, remove or detach one by one. Allows us create Worker without creating its company before
    @JoinColumn(name = "company_id", nullable = false) //nullable false - check for null constraint
    Company company;

    private String firstName;
    private String lastName;

}
