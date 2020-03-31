package com.example.myadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Feedback extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    TextView email,contact,feedback;
    EditText emailr;
    Button send_mail;
    String memail,mcontact,mfeedback,mail_to_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback2);
        email=findViewById(R.id.email);
        contact=findViewById(R.id.contact);
        feedback=findViewById(R.id.feedback);
        emailr=findViewById(R.id.emailr);
        send_mail=findViewById(R.id.btnsendmail);
        final String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        reference=database.getInstance().getReference().child("Feedback").child(sessionId);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           memail=dataSnapshot.child("email").getValue().toString();
           mcontact=dataSnapshot.child("phone").getValue().toString();
           mfeedback=dataSnapshot.child("message").getValue().toString();
           email.setText("Email : "+memail);
           contact.setText("Contact : "+mcontact);
           feedback.setText(mfeedback);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mail_to_send=emailr.getText().toString();
        send_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent=new Intent(Intent.ACTION_VIEW
                , Uri.parse("mailto:" +email));
                intent.putExtra(Intent.EXTRA_SUBJECT,"Mail for your feedback");
                intent.putExtra(Intent.EXTRA_TEXT,mail_to_send);
                startActivity(intent);*/
                SendMail sm=new SendMail(Feedback.this,"namanbadkul77@gmail.com","Mail regarding your feedback","this is for checcking");
                sm.execute();
            }
        });
    }
}
