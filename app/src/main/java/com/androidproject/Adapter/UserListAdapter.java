package com.androidproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.Model.User;
import com.androidproject.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private Context context;
    ArrayList<User> userArrayList=new ArrayList<>();

    public UserListAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.MyViewHolder holder, int position) {
      final User user=userArrayList.get(position);

       holder.tv_name.setText(user.getName());
       holder.tv_contact_no.setText(user.getContact_no());
       holder.tv_address.setText(user.getAddress());

       holder.cardview.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(context, "Click-"+user.getUser_id(), Toast.LENGTH_SHORT).show();
           }
       });

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_contact_no,tv_address;
        MaterialCardView cardview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name=itemView.findViewById(R.id.tv_name);
            tv_contact_no=itemView.findViewById(R.id.tv_contact_no);
            tv_address=itemView.findViewById(R.id.tv_address);
            cardview=itemView.findViewById(R.id.cardview);

        }
    }


}
