package com.example.authentication.repositories;


//Singleton Pattern

import android.arch.lifecycle.MutableLiveData;

import com.example.authentication.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    private static UsersRepository usersRepository = null;
    private List<User> users = new ArrayList<>();

   public static UsersRepository getInstance() {
        if (usersRepository == null)
            usersRepository = new UsersRepository();
        return usersRepository;
    }

    public MutableLiveData<List<User>> getUsers() {
        setDummyUsers();
        MutableLiveData<List<User>> data = new MutableLiveData<>();
        data.setValue(users);
        return data;
    }

    private void setDummyUsers() {
        for (int i = 1; i < 10; i++) {
            users.add(new User("Shariq " + i, "shariq@wi.com " + i));
        }
    }
}
