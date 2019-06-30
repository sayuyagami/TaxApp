package com.app.taxapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Adminhome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);

        Button data = (Button)findViewById(R.id.data);
        Button Ebill = (Button)findViewById(R.id.Ebill);
        Button Wbill = (Button)findViewById(R.id.Wbill);
        Button PTbill = (Button)findViewById(R.id.PTbill);
        Button complaint = (Button)findViewById(R.id.complaint);
        Button book = (Button)findViewById(R.id.book);
        Button fback = (Button)findViewById(R.id.fback);
        Button logout = (Button)findViewById(R.id.logout);

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adminhome.this,Datapage.class);
                startActivity(intent);
            }
        });

        Ebill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adminhome.this,AddEbill.class);
                startActivity(intent);
            }
        });

        Wbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adminhome.this,AddWbill.class);
                startActivity(intent);
            }
        });

        PTbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adminhome.this,AddPTbill.class);
                startActivity(intent);
            }
        });

        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adminhome.this,Complaintdata.class);
                startActivity(intent);
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adminhome.this,AddBookings.class);
                startActivity(intent);
            }
        });

        fback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adminhome.this,Feedbackdata.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Adminhome.this,Admin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                Toast.makeText(Adminhome.this,"logged out Successfully",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }
}
