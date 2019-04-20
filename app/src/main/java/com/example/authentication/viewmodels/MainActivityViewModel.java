package com.example.authentication.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;

import com.example.authentication.models.User;
import com.example.authentication.repositories.UsersRepository;

import java.util.List;


public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();
    private UsersRepository usersRepository;

    public void init() {
        if (users != null) {
            return;
        }
        usersRepository = UsersRepository.getInstance();
        users = usersRepository.getUsers();

    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public LiveData<Boolean> getIsUpdating() {
        return isUpdating;
    }


    public void addUser(final User user) {
        isUpdating.setValue(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isUpdating.setValue(false);
                users.getValue().add(user);
                users.postValue(users.getValue());
            }
        }, 2000);

    }
}
