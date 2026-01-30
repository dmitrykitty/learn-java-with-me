package com.dnikitin.entity.onetoone_primarykey;

import com.dnikitin.entity.manytoone_onetomany.Worker;
import com.dnikitin.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OneToOnePkRunner {
    static void main() {


        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            WorkerOneToOnePk worker = WorkerOneToOnePk.builder()
                    .firstName("First Name")
                    .lastName("Last Name")
                    .build();

            session.persist(worker);

            WorkerInfoOneToOnePk workerInfo = WorkerInfoOneToOnePk.builder()
                    .email("email@gmail.com")
                    .phoneNumber("123456789")
                    .worker(worker)
                    .build();

            workerInfo.setWorker(worker);

            session.persist(workerInfo);

            WorkerOneToOnePk workerOneToOnePk = session.find(WorkerOneToOnePk.class, 7);
            System.out.println(workerOneToOnePk);

            session.getTransaction().commit();
        }
    }
}
