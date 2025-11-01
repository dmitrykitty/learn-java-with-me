package collections.sorting.practise.practise3.comparators;
import collections.sorting.practise.practise3.Chat;

import java.util.Comparator;

public class ChatNameComparator implements Comparator<Chat> {
    @Override
    public int compare(Chat o1, Chat o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
