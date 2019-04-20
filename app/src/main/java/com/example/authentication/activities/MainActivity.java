package com.example.authentication.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.authentication.R;
import com.example.authentication.adapters.UserAdapter;
import com.example.authentication.models.User;
import com.example.authentication.viewmodels.MainActivityViewModel;


import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UserAdapter adapter;
    FloatingActionButton add;
    MainActivityViewModel mainActivityViewModel;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        //getting repository to be initialized for getting data from source
        mainActivityViewModel.init();
        initRecyclerView();
        add = findViewById(R.id.floatingButton);
        progressBar = findViewById(R.id.progressBar);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityViewModel.addUser(new User("Shariq " + new Random().nextInt(1000), "email@email.com"));
            }
        });

        mainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    showProgressBar();
                } else
                    hideProgressBar();
            }
        });
        //binding observer

        mainActivityViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(mainActivityViewModel.getUsers().getValue(), this);
        recyclerView.setAdapter(adapter);
    }
}
