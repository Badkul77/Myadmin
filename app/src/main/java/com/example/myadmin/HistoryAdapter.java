package com.example.myadmin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<MyHistory>  {

    Context c;
    ArrayList<HistoryModel> history;

    public HistoryAdapter(Context c, ArrayList<HistoryModel> historyModels) {
        this.c = c;
        this.history = historyModels;
    }


    @NonNull
    @Override
    public MyHistory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history,null);//this line inflate our row
        return new MyHistory(view);//this will return our view to holder classreturn null;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHistory holder, final int position) {

        holder.detailsHistory.setText(String.valueOf(history.get(position).getDetails()));
        holder.issueHistory.setText(String.valueOf(history.get(position).getIssue()));
        holder.statusHistorty.setText(history.get(position).getStatus());
        holder.id.setText(history.get(position).getId());
        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Help And Support").child(history.get(position).getUserid()).child(history.get(position).getId());
                reference.child("status").setValue("close");
                holder.statusHistorty.setText("close");
                Toast.makeText(c, "Success  "+history.get(position).getId(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return history.size();
    }
}
