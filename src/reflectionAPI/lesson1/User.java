package reflectionAPI.lesson1;

import java.io.Serializable;

public class User extends Person implements Serializable, Comparable<User> {
    private String name;
    public User(long id, String name) {
        this.name = name;
        super(id);
    }

    @Override
    public int compareTo(User o) {
        return Long.compare(this.getId(), o.getId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
