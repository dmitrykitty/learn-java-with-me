package com.dnikitin;

import com.dnikitin.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategySnakeCaseImpl;
import org.hibernate.cfg.Configuration;


import java.time.LocalDate;

public class HibernateRunner {
    static void main() {

        //Session == Connection
        //SessionFactory == pool

        Configuration configuration = new Configuration().configure();
        configuration.configure(); //by default hibernate.cfg.xml\
        //configuration.addAnnotatedClass(User.class);
        //configuration.setPhysicalNamingStrategy(new PhysicalNamingStrategySnakeCaseImpl()); we can set a type of name mapping

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()){
            session.beginTransaction();

            User user1 = User.builder()
                    .username("admin@gmail.com")
                    .firstName("admin")
                    .lastName("admin")
                    .birthDate(LocalDate.of(2001, 3, 9))
                    .age(24)
                    .build();
            //to make this entity available to save - add it to configuration throught configure or xml

            //to save the object
            session.persist(user1);

            session.getTransaction().commit();

        }
    }
}
