package com.dnikitin.entity.onetoone;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "worker_one_to_one")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WorkerOneToOne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToOne(mappedBy = "worker", cascade = CascadeType.ALL)
    private WorkerInfoOneToOne workerInfoOneToOne;

    @OneToOne(mappedBy = "worker", cascade = CascadeType.ALL)
    private Address address;
}
