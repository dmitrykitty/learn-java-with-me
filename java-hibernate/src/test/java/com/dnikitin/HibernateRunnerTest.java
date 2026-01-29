package com.dnikitin;

import com.dnikitin.entity.manytoone_onetomany.Company;
import com.dnikitin.entity.manytoone_onetomany.Worker;
import com.dnikitin.entity.user.User;
import com.dnikitin.util.HibernateUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class HibernateRunnerTest {

    @Test
    void checkReflectionApi() {
        User user = User.builder()
                .username("admin@gmail.com")
                .firstName("admin")
                .lastName("admin")
                .build();

        String INSERT_SQL = """
                insert
                into
                    %s
                    (%s)
                values
                    (%s)
                """;

        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());

        Field[] declaredFields = user.getClass().getDeclaredFields();

        //naming strategy is ignored
        String columnsNames = Arrays.stream(declaredFields).
                map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                .collect(Collectors.joining(", "));

        String values = Arrays.stream(declaredFields).
                map(field -> "?")
                .collect(Collectors.joining(", "));

        INSERT_SQL = String.format(INSERT_SQL, tableName, columnsNames, values);
        System.out.println(INSERT_SQL);
    }

    @Test
    void checkOrphanRemoval(){
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Company company = session.getReference(Company.class, 1);
            company.getWorkers().removeIf(worker -> worker.getFirstName().equals("Pavel"));

            session.getTransaction().commit();
        }
    }

}