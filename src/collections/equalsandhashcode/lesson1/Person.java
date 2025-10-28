package collections.equalsandhashcode.lesson1;

import java.util.Objects;

public class Person {

    private final int id;
    private String firstName;
    private int age;


    public Person(int id, String firstName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && age == person.age && Objects.equals(firstName, person.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, age);
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }
}
