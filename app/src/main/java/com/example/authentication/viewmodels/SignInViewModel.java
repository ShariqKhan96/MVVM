package com.example.authentication.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.authentication.models.User;

import java.util.List;

public class SignInViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;

    private LiveData<List<User>> getUsers() {
        return users;
    }

    private void init() {
    }
}
