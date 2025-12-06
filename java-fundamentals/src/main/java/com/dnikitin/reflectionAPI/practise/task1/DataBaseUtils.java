package com.dnikitin.reflectionAPI.practise.task1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DataBaseUtils {
    private DataBaseUtils() {
    }

    public static String insertInto(Car car) {
        String template = "INSERT INTO %s.%s (%s) VALUES (%s);";

        Table table = car.getClass().getAnnotation(Table.class); //-> Table annotation

        Field[] fields = car.getClass().getDeclaredFields();
        String columnNames= Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(Column.class)) //dla field z annotation
                .map(field -> field.getAnnotation(Column.class))//dostajemy annotacje
                .map(Column::columnName)//dostajemy pole z annotacji
                .collect(Collectors.joining(", "));//łaczymy: brand, model

        String fieldValues= Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(Column.class))
                .peek(field -> field.setAccessible(true)) //nic nie zwraca, wiec peek
                .map(field -> {
                    try {
                        return String.valueOf(field.get(car));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return "";
                    }
                }) //dostajemy wartosci fieldow
                .map(value -> "'" + value + "'")
                .collect(Collectors.joining(", ")); // -> 'BMW', 'x5'


        String fieldValuesFromGetters= Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> getMethodByName(car, field))//dostajemy metodę po nazwie
                .map(method -> {
                    try {
                        return method.invoke(car);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                })//wywołujemy tą metode i dostajemy wartosci z getterow
                .map(value -> "'" + value + "'")
                .collect(Collectors.joining(", "));
        return String.format(template, table.schema(), table.value(), columnNames, fieldValuesFromGetters);


    }

    private static Method getMethodByName(Car car, Field field)  {
        String name  = field.getName();
        try {
            return car.getClass().getMethod("get"
                    + field.getName().substring(0, 1).toUpperCase()
                    + field.getName().substring(1)
            );
        } catch (NoSuchMethodException e){
            throw new RuntimeException(e);
        }
    }
}
