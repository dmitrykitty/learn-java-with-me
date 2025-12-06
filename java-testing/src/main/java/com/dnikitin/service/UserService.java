package com.dnikitin.service;

import com.dnikitin.dto.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final List<User> users = new ArrayList<>();

    public List<User> getAll(){
        return users;
    }

    public boolean add(User user) {
        if(user == null){
            throw new NullPointerException("User is NULL");
        }
        return users.add(user);
    }

    public Optional<User> getUserByName(String name){
        for (User user : users) {
            if(user.getName().equals(name)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
