package com.example.techlab.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.techlab.R;
import com.example.techlab.databinding.ActivityUsersManagementItemBinding;
import com.example.techlab.model.Users;

import java.util.List;

public class UsersManagementAdapter extends RecyclerView.Adapter<UsersManagementAdapter.ViewHolder> {
    private List<Users> userss;
    private Context context;
    public UsersManagementAdapter(List<Users> users, Context context) {
        this.userss = users;
        this.context = context;
    }
    @NonNull
    @Override
    public UsersManagementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ActivityUsersManagementItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext())
                        , R.layout.activity_users_management_item
                        ,viewGroup
                        ,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Users users = userss.get(i);
        viewHolder.usersManagementBinding.setUsersItem(users);
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, Product_ItemManagementActivity.class);
//                intent.putExtra("ID_",users.getId());
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userss.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayout;
        // Binding variables
        public ActivityUsersManagementItemBinding usersManagementBinding;

        public ViewHolder(ActivityUsersManagementItemBinding UsersManagementBinding) {
            super(UsersManagementBinding.getRoot());
            usersManagementBinding = UsersManagementBinding;
            relativeLayout = itemView.findViewById(R.id.usersItemView);

        }
    }
}
