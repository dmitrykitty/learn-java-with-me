package com.dnikitin.collections.sorting.practise.practise2;

import java.util.*;

public class StudetnArrayRunner {
    static void main() {

        List<Student> students = new ArrayList<>(Arrays.asList(
                new Student("Dima", "Nikitin", 23, 4.60),
                new Student("Kristinka", "Mironenka", 24, 4.05),
                new Student("Pawel", "Kaplica", 25, 3.95),
                new Student("Ada", "Kowalska", 26, 3.85),
                new Student("Dima", "Bolgov", 20, 4.92)
        ));

        System.out.println(students);
        Collections.sort(students);
        System.out.println(students);

        students.sort(new StudentAgeComporator());
        System.out.println(students);

        students.sort(Comparator.comparing(Student::getAvgGrade)
                .thenComparing(Student::getLastName)
                .thenComparing(Student::getFirstName));

        System.out.println(students);

        long startTime = System.nanoTime();
        StudentUtils.getBestStudent1(students);
        long endTime = System.nanoTime();
        long res1 = endTime - startTime;
        System.out.println("Time for getBestStudent1: " + res1);

        startTime = System.nanoTime();
        StudentUtils.getBestStudent2(students);
        endTime = System.nanoTime();
        long res2 = endTime - startTime;
        System.out.println("Time for getBestStudent2: " + res2);

        System.out.println(res1 / res2);


    }


    public static class StudentAgeComporator implements java.util.Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return -Integer.compare(o1.getAge(), o2.getAge()); //reversed order
        }
    }



}
