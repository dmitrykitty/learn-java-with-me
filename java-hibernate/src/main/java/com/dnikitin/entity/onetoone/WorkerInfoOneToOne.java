package com.dnikitin.entity.onetoone;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "worker")
@EqualsAndHashCode(exclude = "worker")
@Entity
@Table(name = "worker_info_one_to_one")
public class WorkerInfoOneToOne {

    @Id
    @Column(name = "worker_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "worker_id")
    @MapsId //allows us to not add method setWorker
    private WorkerOneToOne worker;

    @Column(nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    public void setWorker(WorkerOneToOne worker) {
        worker.setWorkerInfoOneToOne(this);
        this.worker = worker;
        this.id = worker.getId();
    }
}
