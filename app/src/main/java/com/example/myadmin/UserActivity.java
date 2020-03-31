package com.example.myadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
     RecyclerView recyclerView;
     UserAdapter userAdapter;
    FirebaseDatabase database;
    DatabaseReference reference,ds1;
     ArrayList<UserProfile> al;
    String name,email,carNumber,carModel;
    int id;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        reference=database.getInstance().getReference().child("UserProfile");
      recyclerView=findViewById(R.id.recycleview);
      recyclerView.setHasFixedSize(true);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      al=new ArrayList<>();
      al.add(new UserProfile("wqdqwd","dwqdqw","dsddef","weewf","nMbeWrlD24hrOz3eKXQnegoPqkC3"));
        userAdapter = new UserAdapter(UserActivity.this, al);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    id=(int) dataSnapshot.getChildrenCount();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        user=ds.getKey();
                        //arrayList.add(user);
                      //  arrayAdapter.notifyDataSetChanged();
                        ds1=database.getInstance().getReference().child("UserProfile").child(user);
                       /*try {
                           name = "Name:"+ds1.child("name").getValue().toString();
                           email = "Email:"+ds.child(user).child("email").getValue().toString();
                           carNumber = "Car Number:"+ds.child(user).child("carNumber").getValue().toString();
                           carModel = "CarModel"+ds.child(user).child("carModel").getValue().toString();
                           al.add(new UserProfile(name, email, carNumber, carModel));
                           userAdapter.notifyDataSetChanged();
                       }
                       catch (Exception e)
                       {
                           Log.d("MyAccountn","exception"+e);
                       }*/
                       ds1.addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try {
                                 name = dataSnapshot.child("name").getValue().toString();
                                 email = dataSnapshot.child("email").getValue().toString();
                                 carNumber = dataSnapshot.child("carNumber").getValue().toString();
                                 carModel = dataSnapshot.child("carModel").getValue().toString();
                                 //new UserProfile(user);
                                user=dataSnapshot.getKey();
                                al.add(new UserProfile(name, email, carNumber, carModel,user));
                                userAdapter.notifyDataSetChanged();
                            }
                            catch (Exception e)
                            {
                                Log.d("MyAccountn","exception"+e);
                            }

                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError databaseError) {

                           }
                       });
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        recyclerView.setAdapter(userAdapter);
    }
}
