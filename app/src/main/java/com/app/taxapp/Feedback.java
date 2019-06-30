package com.app.taxapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends Settings {

    EditText name,eid,feed;
    FeedbackMembers members;
    Button feedbtn;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        name=(EditText)findViewById(R.id.name);
        eid=(EditText)findViewById(R.id.eid);
        feed=(EditText)findViewById(R.id.fb);
        feedbtn=(Button)findViewById(R.id.feedbtn);
        members=new FeedbackMembers();
        reff= FirebaseDatabase.getInstance().getReference().child("FeedbackMembers");

        feedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                members.setName(name.getText().toString().trim());
                members.setEmail(eid.getText().toString().trim());
                members.setSubject(feed.getText().toString().trim());

                reff.push().setValue(members);
                Toast.makeText(Feedback.this,"Feedback sent Successfully !!",Toast.LENGTH_LONG).show();
            }
        });
    }

}
