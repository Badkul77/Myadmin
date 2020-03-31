package com.example.myadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Token extends AppCompatActivity {
//ListView ml;
   // ArrayList<String> arrayList=new ArrayList<>();
   // ArrayAdapter<String> arrayAdapter;
    ArrayList<HistoryModel>histMod=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    HistoryAdapter historyAdapter=new HistoryAdapter(Token.this,histMod);
    RecyclerView recyclerView;
    String name,user;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);
      //  ml=findViewById(R.id.mlists);
        recyclerView=findViewById(R.id.recycleview);
     recyclerView.setHasFixedSize(true);
     recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");

       // arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
       // ml.setAdapter(arrayAdapter);

        reference=database.getInstance().getReference().child("Help And Support").child(sessionId);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    for (DataSnapshot ds : dataSnapshot.getChildren())
                    {
                        user=ds.getKey().toString();

                        String details = dataSnapshot.child(user).child("Details").getValue().toString();
                        String issue = dataSnapshot.child(user).child("Issue").getValue().toString();
                        String status = dataSnapshot.child(user).child("status").getValue().toString();
                        histMod.add(new HistoryModel(details,issue,status,user,sessionId));
                        //histMod.add(user);
                        recyclerView.setAdapter(historyAdapter);
                        historyAdapter.notifyDataSetChanged();
                     /*   name=ds.getKey();
                        String value=ds.getValue().toString();
                        //arrayList.add(name+"  "+value);
                        arrayAdapter.notifyDataSetChanged();*/
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       /* ml.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = ((TextView)view).getText().toString();
                String ids=item.substring(0,7);
                reference.child(ids).child("status").setValue("close");
                Toast.makeText(Token.this, ""+ids, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

}
