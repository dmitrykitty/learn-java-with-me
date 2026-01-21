package com.dnikitin;

import com.dnikitin.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

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

}