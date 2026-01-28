package com.dnikitin.entity.manytoone_onetomany;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "workers")
@EqualsAndHashCode(exclude = "workers") //exlude this fields to stop cycling inside my objects for hashcode collections(set, map)
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String name;

    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    //@JoinColumn(name = "company_id") we can skeep it if add mappedBy
    //Lazy initialisation (by default) with PersistentBag (Proxy for collection)
    private Set<Worker> workers =  new HashSet<>();

    //nice to have this method to add connection between company and worker
    public void addWorker(Worker worker) {
        workers.add(worker);
        worker.setCompany(this);
    }
}
