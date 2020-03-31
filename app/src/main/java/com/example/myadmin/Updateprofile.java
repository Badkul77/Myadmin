package com.example.myadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Updateprofile extends AppCompatActivity {
    private EditText newUserName, newUserEmail, newUserCarNumber,newUserCarModel;
    private Button save;
    private Button back;
    private FirebaseDatabase firebaseDatabase;
    private ImageView updateProfilePic;
    DatabaseReference databaseReference;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
   // private StorageReference storageReference;
    //private FirebaseStorage firebaseStorage;
    String name,email,carNumber,carModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);
        final String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        //Toast.makeText(this, ""+sessionId, Toast.LENGTH_SHORT).show();

        newUserName = findViewById(R.id.etNameUpdate);
        newUserEmail = findViewById(R.id.etEmailUpdate);
        newUserCarNumber = findViewById(R.id.etCarNumber);
        newUserCarModel = findViewById(R.id.etCarModel);
        save = findViewById(R.id.btnSave);
        updateProfilePic = findViewById(R.id.ivProfileUpdate);


        firebaseDatabase = FirebaseDatabase.getInstance();


        //firebaseStorage = FirebaseStorage.getInstance();
       // storageReference = firebaseStorage.getReference();

          databaseReference = firebaseDatabase.getReference().child("UserProfile").child(sessionId);
      /*  updateProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("images/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
            }
        });*/

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = newUserName.getText().toString();
                email = newUserEmail.getText().toString();
                carNumber = newUserCarNumber.getText().toString();
                carModel = newUserCarModel.getText().toString();
                if (name.isEmpty() || email.isEmpty() || carNumber.isEmpty() || carModel.isEmpty()) {
                    newUserName.setError("enter name");
                    newUserEmail.setError("enter email");
                    newUserCarNumber.setError("enter carNumber");
                    newUserCarModel.setError("enter carModel");
                } else {
                    databaseReference.child("name").setValue(name);
                    databaseReference.child(("email")).setValue(email);
                    databaseReference.child("carNumber").setValue(carNumber);
                    databaseReference.child("carModel").setValue(carModel);
                }
                Toast.makeText(Updateprofile.this, "Succesfully Updated", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    String name = dataSnapshot.child("name").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();
                    String carNumber = dataSnapshot.child("carNumber").getValue().toString();
                    String carModel = dataSnapshot.child("carModel").getValue().toString();
                    newUserName.setText(name);
                    newUserEmail.setText(email);
                    newUserCarNumber.setText(carNumber);
                    newUserCarModel.setText(carModel);
                }catch (Exception e){
                    Log.d("MyAccountn","exception"+e);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
    }
}
