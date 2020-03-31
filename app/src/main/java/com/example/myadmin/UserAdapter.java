package com.example.myadmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    Context context;
    ArrayList<UserProfile> al;
    public UserAdapter(Context context, ArrayList<UserProfile>al){
        this.context = context;
        this.al = al;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.profile,null);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {
        final UserProfile order=al.get(position);
        //holder.image.setImageResource(order.imageid);
        holder.name.setText("Name :"+order.userName);
        holder.email.setText("Email :"+order.userEmail);
        holder.carno.setText("Car Number:"+order.userCarNumber);
        holder.carmodel.setText("Car Model :"+order.userCarModel);
        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu= new PopupMenu(context,holder.option);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.Update:
                            {
                                Toast.makeText(context, "Update :"+order.getUserId(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, Updateprofile.class);
                                intent.putExtra("EXTRA_SESSION_ID", order.getUserId());
                                context.startActivity(intent);
                                break;
                            }
                            case R.id.block:
                            {
                                Toast.makeText(context, "Block", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,email,carno,carmodel,option;
        public UserViewHolder(@NonNull View v) {
            super(v);
            image= v.findViewById(R.id.imageView);
            name=v.findViewById(R.id.textViewname);
            email=v.findViewById(R.id.textViewemail);
            carno=v.findViewById(R.id.textcarno);
            carmodel=v.findViewById(R.id.textViewcarcolor);
            option=v.findViewById(R.id.option);
        }
    }
}