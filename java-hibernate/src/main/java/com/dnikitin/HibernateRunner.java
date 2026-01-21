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
                    .info("""
                            {
                            "name": "Jack",
                            "salary": 12353
                            }
                            """)
                    .build();
            //to make this entity available to save - add it to configuration throught configure or xml

            //to save the object
            //session.persist(user1);

            //merge(entity) -> update
            //remove(entity) -> delete
            //find(Class, id)

            User user2 = User.builder()
                    .username("admin@gmail.com")
                    .firstName("kris")
                    .lastName("moja")
                    .birthDate(new Birthday(LocalDate.of(2001, 3, 9)))
                    .role(Role.ADMIN)
                    .info("""
                            {
                            "name": "Krisik",
                            "salary": 12353
                            }
                            """)
                    .build();

            User merge = session.merge(user2); //return new modified object
            //if user does't exists - create new one

            //session.remove(merge); no exception if doesn't exist

            //mapping from result set to user.
            User user = session.find(User.class, user2.getUsername());
            //before close the transaction Hibernate execute flush() and it automatically update columns values inside DB
            user.setLastName("Nikitina");

            System.out.println(session.isDirty()); //true
            //flush sends all changes to our DB
            session.flush();
            System.out.println(session.isDirty()); //false
            System.out.println(user);

            //session.evict(user2); //remove entity from 1st level cache
            //session.clear(); //remove all entities from 1st level cache



            session.getTransaction().commit();

        }
    }
}
