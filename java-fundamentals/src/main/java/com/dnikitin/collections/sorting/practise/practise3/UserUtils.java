package com.dnikitin.collections.sorting.practise.practise3;

import java.util.Iterator;
import java.util.List;

public class UserUtils {
    private UserUtils() {
    }

    public static double getAvgAge(List<User> users) {
        long sum = 0;
        for (Iterator<User> it = users.iterator(); it.hasNext(); ) {
            User currentUser = it.next();
            sum += currentUser.age();
        }
        return (double) sum / users.size();
    }
}
