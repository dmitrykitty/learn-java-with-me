package com.dnikitin.collections.sorting.practise.practise3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChatUtils {
    private ChatUtils() {}


    public static List<User> getListOfAdults(List<Chat> chats){
        List<User> adults = new ArrayList<>();
        for(Chat chat : chats){
            for(User user : chat.getUsersList()){
                if(user.age() > 18){
                    adults.add(user);
                }
            }
        }
        return adults;
    }

    public static boolean deleteSmallChats1(List<Chat> chats){
        int deletedChats = 0;
        for(Iterator<Chat> it = chats.iterator(); it.hasNext();){
            Chat currentChat = it.next();
            if(currentChat.getUsersAmount() < 1000){
                it.remove();
                deletedChats++;
            }
        }
        return deletedChats > 0;
    }

    public static boolean deleteSmallChats2(List<Chat> chats){
        return chats.removeIf(chat -> chat.getUsersAmount() < 1000);
    }

    private static boolean isSmallChat(Chat chat){
        return chat.getUsersAmount() < 1000;
    }

    public static boolean deleteSmallChats3(List<Chat> chats){
        return chats.removeIf(ChatUtils::isSmallChat);
    }
}
