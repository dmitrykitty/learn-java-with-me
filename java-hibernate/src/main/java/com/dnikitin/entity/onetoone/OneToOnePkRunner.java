package com.dnikitin.entity.onetoone;

import com.dnikitin.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OneToOnePkRunner {
    static void main() {


        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            WorkerOneToOne worker = WorkerOneToOne.builder()
                    .firstName("First Name")
                    .lastName("Last Name")
                    .build();

            Address address = Address.builder()
                    .city("City")
                    .street("Street")
                    .number(2)
                    .build();
            // Establish the relationship on the owning side.
            // Address will hold the foreign key to Worker.
            address.setWorker(worker);

            //we can save worker after creatring adress because we don't need worker id as primary key
            //cascade type allows us to save address and worker together
            //session.persist(worker);


            WorkerInfoOneToOne workerInfo = WorkerInfoOneToOne.builder()
                    .email("email@gmail.com")
                    .phoneNumber("123456789")
                    .build();

            //i need id because primary key inside info = foreign key to worker id, so inside set I need to know worker ID
            // Critical step: Link the entities.
            // @MapsId in WorkerInfo ensures that Worker's PK is copied to WorkerInfo's PK/FK.
            workerInfo.setWorker(worker);

            //session.persist(workerInfo);
            //by using @MapsId in the Info and Cascade.ALL I can create just worker and address and info will be created
            //automatically. Morever worker id will be send to INFO before flush so it's no need to create worker before INFO
            /*
             * Persisting the root entity (Worker).
             * Due to CascadeType.ALL on both 'address' and 'workerInfo' fields,
             * Hibernate will automatically propagate the persist operation to associated entities.
             * The order of SQL INSERTs is managed by Hibernate to satisfy Foreign Key constraints.
             */
            session.persist(worker);

            WorkerOneToOne workerOneToOne = session.find(WorkerOneToOne.class, 7);
            System.out.println(workerOneToOne);

            session.getTransaction().commit();
        }
    }
}
