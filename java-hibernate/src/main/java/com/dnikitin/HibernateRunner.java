package com.dnikitin;

import com.dnikitin.entity.user.Birthday;
import com.dnikitin.entity.user.Role;
import com.dnikitin.entity.user.User;

import com.dnikitin.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.time.LocalDate;

@Slf4j
public class HibernateRunner {
    //private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);

    static void main() {

        //Session == Connection
        //SessionFactory == pool

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession();
             Session session2 = sessionFactory.openSession()) {
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
            log.info("User entity {} is in transient state", user1);

            //=================================CRUD====================================

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
            log.info("User entity {} is in persistence state inside session {}", user, session);
            //before close the transaction Hibernate execute flush() and it automatically update columns values inside DB

            //=================================1ST-LEVEL-CACHE====================================
            user.setLastName("Nikitina");

            System.out.println(session.isDirty()); //true
            //flush sends all changes to our DB
            session.flush();
            System.out.println(session.isDirty()); //false
            System.out.println(user);

            //session.evict(user2); //remove entity from 1st level cache
            //session.clear(); //remove all entities from 1st level cache

            session.getTransaction().commit();

            //===================================ENTITY-LIFECYCLE=====================================

            //new entity -> TRANSIENT --- merge(), persistence() --- > PERSISTENT ----remove()---> REMOVED
            //    \                                                  /          \
            //      -------------------- get() ---------------------             ------ evict(), clear(), close(), merge() --> DETACHED
            //NOTE: entity has state according to session!!!
            //if removew -> firstly get (changed state to PERSISTENT) and then remove (changed state to REMOVED)
            session2.beginTransaction();

            //TRANSIENT
            User test1 = User.builder()
                    .username("test1@gmail.com")
                    .firstName("test1")
                    .build();

            //session2.persist(test1); //test1 already PERSISTENT
            test1.setLastName("test1LN");
            //set all values from DB to Entity (get fresh test, old test set values from fresh test)
            session2.refresh(test1);

            session2.getTransaction().commit();
            log.warn("Session is flushed inside transaction {}", session2.getTransaction());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
