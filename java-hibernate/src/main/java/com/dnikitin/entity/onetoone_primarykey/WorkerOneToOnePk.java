package com.dnikitin.entity.onetoone_primarykey;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "worker_one_to_one_pk")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WorkerOneToOnePk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToOne(mappedBy = "worker", cascade = CascadeType.ALL)
    private WorkerInfoOneToOnePk workerInfoOneToOnePk;
}
