package com.dnikitin.collections.sorting.practise.practise2;

import java.util.Objects;

public class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private int age;
    private double avgGrade;

    public Student(String firstName, String lastName, int age, double avgGrade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.avgGrade = avgGrade;
    }



    @Override
    public int compareTo(Student o) {
        int res = firstName.compareTo(o.firstName);
        return res == 0 ? lastName.compareTo(o.lastName) : res;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", avgGrade=" + avgGrade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age
                && Double.compare(avgGrade, student.avgGrade) == 0
                && Objects.equals(firstName, student.firstName)
                && Objects.equals(lastName, student.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, avgGrade);
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getAvgGrade() {
        return avgGrade;
    }
}
