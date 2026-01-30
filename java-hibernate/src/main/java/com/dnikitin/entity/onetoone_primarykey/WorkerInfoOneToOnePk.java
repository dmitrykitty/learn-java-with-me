package com.dnikitin.entity.onetoone_primarykey;

import com.dnikitin.entity.manytoone_onetomany.Worker;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "worker")
@EqualsAndHashCode(exclude = "worker")
@Entity
@Table(name = "worker_info_one_to_one_pk")
public class WorkerInfoOneToOnePk {

    @Id
    @Column(name = "worker_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "worker_id")
    @MapsId //allows us to not add method setWorker
    private WorkerOneToOnePk worker;

    @Column(nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    public void setWorker(WorkerOneToOnePk worker) {
        worker.setWorkerInfoOneToOnePk(this);
        this.worker = worker;
        this.id = worker.getId();
    }
}
