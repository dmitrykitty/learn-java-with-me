package com.dnikitin.entity.manytoone_onetomany;

import com.dnikitin.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Slf4j
public class ManyToOneRunner {
    static void main() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();


            Company company = Company.builder()
                    .name("Apple")
                    .build();

            Worker worker = Worker.builder()
                    .firstName("Pavel")
                    .lastName("Nikitin")
                    .company(company)
                    .build();

            //session.persist(company);
            //session.persist(worker);

            Worker worker1 = session.find(Worker.class, 1L);
            //used left outer join for worker left join company if company_id is nullable
            //if nullable=false -> inner join
            //Worker unproxy = (Worker)Hibernate.unproxy(worker1); //method to get real object from its proxy
            worker1.setFirstName("Dima");
            log.info("User updated: {}", worker1);


            session.flush();

            session.find(Company.class, 1);



            Company company1 = Company.builder()
                    .name("Meta")
                    .build();

            Worker test1 = Worker.builder()
                    .firstName("test1")
                    .company(company1)
                    .build();

            company1.addWorker(test1);

            Worker test2 = Worker.builder()
                    .firstName("test1")
                    .company(company1)
                    .build();
            company1.addWorker(test2);

            Worker test3 = Worker.builder()
                    .firstName("test3")
                    .company(company1)
                    .build();
            company1.addWorker(test3);

            session.persist(company1); //everything saved thanks to Cascade.All

            session.getTransaction().commit();
        }
    }
}
