package com.dnikitin.entity.onetoone;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = "worker")
@EqualsAndHashCode(exclude = "worker")
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;
    private String street;
    private String city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_id")
    private WorkerOneToOne worker;

    public void setWorker(WorkerOneToOne worker) {
        this.worker = worker;
        worker.setAddress(this);
    }


}
