package com.dnikitin.collections.sorting.practise.practise3.comparators;
import com.dnikitin.collections.sorting.practise.practise3.Chat;

import java.util.Comparator;
public class ChatUsersAmountComparator implements Comparator<Chat>{
    @Override
    public int compare(Chat o1, Chat o2) {
        return Integer.compare(o1.getUsersAmount(), o2.getUsersAmount());
    }
}
