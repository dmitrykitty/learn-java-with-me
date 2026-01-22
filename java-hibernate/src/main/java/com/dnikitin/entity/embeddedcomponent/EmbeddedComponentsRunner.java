package com.dnikitin.entity.embeddedcomponent;

import com.dnikitin.entity.user.Birthday;
import com.dnikitin.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

@Slf4j
public class EmbeddedComponentsRunner {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
            Session session = sessionFactory.openSession()){
            session.beginTransaction();

            User1 user1 = User1.builder()
                    .username("user1.1@gmail.com")
                    .personalInfo(PersonalInfo.builder()
                            .name("John")
                            .surname("Doe")
                            .phoneNumber("123456789")
                            .build())
                    .birthDate(new Birthday(LocalDate.of(1998, 11, 28)))
                    .build();
            log.info("Creating user 1 with birth date {}", user1.getBirthDate());

            session.persist(user1);
            log.info("User1 persisted successfully");

            session.getTransaction().commit();
        }
    }
}
