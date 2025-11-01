package collections.sorting.practise.practise3;

import java.util.List;

public class Chat implements Comparable<Chat>{

    private final String name;
    private List<User> usersList;

    @Override
    public int compareTo(Chat o) {
        return name.compareTo(o.name);
    }


    public Chat(String name, List<User> usersList) {
        this.name = name;
        this.usersList = usersList;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "name='" + name + '\'' +
                ", usersList=" + usersList +
                '}';
    }


    public String getName() {
        return name;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public int getUsersAmount() {
        return usersList.size();
    }
}
