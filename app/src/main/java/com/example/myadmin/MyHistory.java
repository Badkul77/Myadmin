package com.example.myadmin;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyHistory extends RecyclerView.ViewHolder {

    TextView detailsHistory,issueHistory,statusHistorty,id;
    Button close;

    public MyHistory(@NonNull View itemView) {
        super(itemView);

        this.detailsHistory=itemView.findViewById(R.id.details);
        this.issueHistory=itemView.findViewById(R.id.issue);
        this.statusHistorty=itemView.findViewById(R.id.status);
        this.id=itemView.findViewById(R.id.id);
        this.close=itemView.findViewById(R.id.close);
       // itemView.setOnClickListener(this);
    }

   /* @Override
    public void onClick(View view) {
        this.itemClickListner.onItemClickListner(view,getLayoutPosition());
    }*/
}
