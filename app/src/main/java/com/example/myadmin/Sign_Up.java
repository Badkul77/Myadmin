package com.example.myadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_Up extends AppCompatActivity {
    EditText email,password,username;
    TextView login;
    Button registernow;
    ImageView facebook,twitter;
    private FirebaseAuth mAuth;
    DatabaseReference reference;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        inti();
        mAuth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();


        registernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emails=email.getText().toString();
                String pass=password.getText().toString();
                Register(emails,pass);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Sign_Up.this,Sign_IN.class);
                startActivity(intent);
            }
        });
    }
    void Register(final String emails, final String pass)
    {
        if(TextUtils.isEmpty(emails))
        {
            Toast.makeText(this, "Please Enter a Valid Email Id", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(this, "Please Enter a Valid Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(emails,pass)
                    .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                String uname=username.getText().toString();
                                // String key=reference.push().getKey();
                                //User user=new User(emails,pass);
                                //user.setKey(key);
                                //reference.child(key).child("Emailid").setValue(emails);
                                reference.setValue(uname).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(Sign_Up.this, "Succesfully Registered", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Sign_Up.this, ""+e, Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(Sign_Up.this, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void inti() {
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        username=findViewById(R.id.username);
        registernow=findViewById(R.id.register);
        login=findViewById(R.id.login);
        facebook=findViewById(R.id.facebook);
        twitter=findViewById(R.id.twitter);
    }
}
