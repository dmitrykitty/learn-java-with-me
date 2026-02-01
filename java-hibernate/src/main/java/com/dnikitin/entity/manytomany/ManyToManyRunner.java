package com.dnikitin.entity.manytomany;

import com.dnikitin.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ManyToManyRunner {
    static void main() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            UserManyToMany user = UserManyToMany.builder()
                    .nickname("dmtrkitty")
                    .age(25)
                    .build();

            session.persist(user);

            ChatManyToMany chat = ChatManyToMany.builder()
                    .name("JavaChat")
                    .build();

            user.addChat(chat);

            session.persist(chat);

            session.getTransaction().commit();
        }
    }
}
