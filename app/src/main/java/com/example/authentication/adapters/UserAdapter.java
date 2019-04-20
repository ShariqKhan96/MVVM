package com.example.authentication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.authentication.R;
import com.example.authentication.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyVH> {
    List<User> users = new ArrayList<>();
    Context context;

    public UserAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UserAdapter.MyVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_view, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyVH myVH, int i) {
        User user = users.get(i);
        myVH.email.setText(user.getEmail());
        myVH.name.setText(user.getName());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        TextView name, email;

        public MyVH(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);

        }
    }
}
