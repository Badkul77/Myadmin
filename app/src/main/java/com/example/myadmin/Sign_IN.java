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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_IN extends AppCompatActivity {
    EditText email,password;
    TextView forgot,registernow;
    Button login;
    ImageView facebook,twitter;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__i_n);
        inti();

        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emails=email.getText().toString();
                String pass=password.getText().toString();
                Register(emails,pass);
            }
        });
        registernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Sign_IN.this,Sign_Up.class);
                startActivity(intent);
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emails=email.getText().toString();
                if(TextUtils.isEmpty(emails))
                {
                    Toast.makeText(Sign_IN.this, "Please Enter a Valid Email Id", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuth.sendPasswordResetEmail(emails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Sign_IN.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Sign_IN.this, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

    }

    void Register(final String emails, String pass)
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
            mAuth.signInWithEmailAndPassword(emails,pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Sign_IN.this, "Succesfully Login", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Sign_IN.this,MainActivity.class);
                                intent.putExtra("email",emails);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(Sign_IN.this, "Incorrect Credential", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }


    public void inti() {
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        forgot=findViewById(R.id.forgot_password);
        registernow=findViewById(R.id.register);
        login=findViewById(R.id.login);
        facebook=findViewById(R.id.facebook);
        twitter=findViewById(R.id.twitter);
    }
}
