package com.dnikitin;

import com.dnikitin.entity.Birthday;
import com.dnikitin.entity.Role;
import com.dnikitin.entity.User;
import com.dnikitin.entity.converter.BirthdayConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

        configuration.addAttributeConverter(new BirthdayConverter(), true); //no need to set autoapplay=true
        // if we have it above the converterClass

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()){
            session.beginTransaction();

            User user1 = User.builder()
                    .username("admin@gmail.com")
                    .firstName("admin")
                    .lastName("admin")
                    .birthDate(new Birthday(LocalDate.of(2001, 3, 9)))
                    .role(Role.ADMIN)
                    .build();
            //to make this entity available to save - add it to configuration throught configure or xml

            //to save the object
            session.persist(user1);

            session.getTransaction().commit();

        }
    }
}
