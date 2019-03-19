package com.example.techlableenapp.Controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.techlableenapp.R;
import com.example.techlableenapp.model.UserExampleItem;

import java.util.ArrayList;

public class UserManagementItemAdapter extends RecyclerView.Adapter<UserManagementItemAdapter.UserManagementItemViewHolder> {
    private ArrayList<UserExampleItem> mUserExampleList;
    public static class UserManagementItemViewHolder extends RecyclerView.ViewHolder{
        public TextView userEmail;
        public TextView userName;
        public TextView userLoanedAmount;

        public UserManagementItemViewHolder(@NonNull View itemView) {
            super(itemView);
            userEmail = itemView.findViewById(R.id.UserManagementItemUserEmail);
            userName = itemView.findViewById(R.id.UserManagementItemUserName);
            userLoanedAmount = itemView.findViewById(R.id.UserManagementItemUserProductLoanedAmount);
        }
    }
    public UserManagementItemAdapter(ArrayList<UserExampleItem> userExampleList){
        mUserExampleList = userExampleList;
    }
    @NonNull
    @Override
    public UserManagementItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_example_item, viewGroup,false);
        UserManagementItemViewHolder userManagementItemViewHolder = new UserManagementItemViewHolder(v);
        return  userManagementItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserManagementItemViewHolder userManagementItemViewHolder, int i) {
        UserExampleItem currentItem = mUserExampleList.get(i);
        userManagementItemViewHolder.userName.setText(currentItem.getUserManagementItemUserName());
        userManagementItemViewHolder.userEmail.setText(currentItem.getUserManagementItemUserEmail());
        userManagementItemViewHolder.userLoanedAmount.setText(currentItem.getUserManagementItemUserProductLoanedAmount() + "");
    }
    @Override
    public int getItemCount() {
        return mUserExampleList.size();
    }
}
