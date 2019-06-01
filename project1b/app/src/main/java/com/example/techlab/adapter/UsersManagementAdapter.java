package com.example.techlab.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import com.example.techlab.R;
import com.example.techlab.databinding.TemplateUsersManagementItemBinding;
import com.example.techlab.model.Users;
import com.example.techlab.view.User_management_user_InfoActivity;

import java.util.ArrayList;
import java.util.List;

//All users Management page.
public class UsersManagementAdapter extends RecyclerView.Adapter<UsersManagementAdapter.ViewHolder> implements Filterable {

    private List<Users> usersListFull;
    private Context context;

    public UsersManagementAdapter(List<Users> users, Context context) {
        this.usersListFull = new ArrayList<>(users);
        this.context = context;
    }

    @NonNull
    @Override
    public UsersManagementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TemplateUsersManagementItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext())
                        , R.layout.template_users_management_item
                        ,viewGroup
                        ,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Users current_users = usersListFull.get(i);
        viewHolder.usersManagementBinding.setUsersItem(current_users);
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, User_management_user_InfoActivity.class);
                intent.putExtra("ID_",current_users.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersListFull.size();
    }

    @Override
    public Filter getFilter() {
        return usersFilter;
    }

    private Filter usersFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Users> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(usersListFull);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Users user : usersListFull){
                    if(user.getFirstName().toLowerCase().contains(filterPattern)||user.getSchoolEmail().toLowerCase().contains(filterPattern)){
                        filteredList.add(user);
                    }

                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            usersListFull.clear();
            usersListFull.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayout;
        // Binding variables
        public TemplateUsersManagementItemBinding usersManagementBinding;

        public ViewHolder(TemplateUsersManagementItemBinding UsersManagementBinding) {
            super(UsersManagementBinding.getRoot());
            usersManagementBinding = UsersManagementBinding;
            relativeLayout = itemView.findViewById(R.id.usersItemView);

        }
    }
}
