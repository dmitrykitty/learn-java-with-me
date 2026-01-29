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
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL,  orphanRemoval = true)
    //@JoinColumn(name = "company_id") we can skeep it if add mappedBy
    //CascadeType - what we gonna do with Collection entities when something going with main entity
    //orphanRemoval - if we delete Worker from the Set - should we deleted from DB
    //Lazy initialisation (by default) with PersistentBag (Proxy for collection)
    private Set<Worker> workers =  new HashSet<>();

    //nice to have this method to add connection between company and worker
    public void addWorker(Worker worker) {
        workers.add(worker);
        worker.setCompany(this);
    }
}
