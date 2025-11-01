package collections.sorting.practise.practise3;

import collections.sorting.practise.practise3.comparators.ChatNameComparator;
import collections.sorting.practise.practise3.comparators.ChatUsersAmountComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ChatRunner {
    static void main() {

        User userAdam = new User(101, "Adam", 18);
        User userEwa = new User(102, "Ewa", 28);
        User userPiotr = new User(103, "Piotr", 45);
        User userAnna = new User(104, "Anna", 13);
        User userTomasz = new User(105, "Tomasz", 39);
        User userMaria = new User(106, "Maria", 17);
        User userKrzysztof = new User(107, "Krzysztof", 29);
        User userKatarzyna = new User(108, "Katarzyna", 34);
        User userMarcin = new User(109, "Marcin", 18);
        User userJoanna = new User(110, "Joanna", 26);

        List<Chat> chats1 = new ArrayList<>(Arrays.asList(
                new Chat("JavaLearning", new ArrayList<>(Arrays.asList(userAdam, userEwa, userPiotr))),
                new Chat("PythonBeginners", new ArrayList<>(Arrays.asList(userAnna, userTomasz, userMaria))),
                new Chat("DataScience", new ArrayList<>(Arrays.asList(userKrzysztof, userKatarzyna))),
                new Chat("FrontendDevs", new ArrayList<>(Arrays.asList(userMarcin, userJoanna)))
        ));

        System.out.println(chats1);
        List<User> listOfAdults = ChatUtils.getListOfAdults(chats1);
        System.out.println(listOfAdults);
        double avgAge = UserUtils.getAvgAge(listOfAdults);
        System.out.println("Average age: " + avgAge);

        List<Chat> chats2 = new ArrayList<>(chats1);
        List<Chat> chats3 = new ArrayList<>(chats1);

        long startTime = System.nanoTime();
        ChatUtils.deleteSmallChats1(chats1);
        long endTime = System.nanoTime();
        System.out.println("Time with RemoveChats1: " + (endTime - startTime));
        System.out.println(chats1);

        startTime = System.nanoTime();
        ChatUtils.deleteSmallChats2(chats2);
        endTime = System.nanoTime();
        System.out.println("Time with RemoveChats2: " + (endTime - startTime));
        System.out.println(chats2);

        startTime = System.nanoTime();
        ChatUtils.deleteSmallChats3(chats3);
        endTime = System.nanoTime();
        System.out.println("Time with RemoveChats3: " + (endTime - startTime));
        System.out.println(chats3);

        startTime = System.nanoTime();
        chats1.sort(new ChatNameComparator().thenComparing(new ChatUsersAmountComparator()));
        endTime = System.nanoTime();
        System.out.println("Time with Comparator: " + (endTime - startTime));
        System.out.println(chats1);

        startTime = System.nanoTime();
        chats2.sort(Comparator.comparing(Chat::getName).thenComparing(Chat::getUsersAmount));
        endTime = System.nanoTime();
        System.out.println("Time with functional programming: " + (endTime - startTime));
        System.out.println(chats2);




    }
}
