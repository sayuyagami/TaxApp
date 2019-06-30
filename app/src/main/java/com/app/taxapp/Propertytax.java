package com.app.taxapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Propertytax extends Settings {

    TextInputEditText pti;
    Button proceedbtn1;
    ProgressBar ptbar;
    DatabaseReference reff;

    public static  String ptino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertytax);
        pti = findViewById(R.id.pti);
        proceedbtn1 = findViewById(R.id.proceedbtn1);
        ptbar = (ProgressBar)findViewById(R.id.ptbar);

        proceedbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ptino = pti.getText().toString().trim();
                if (!ptino.isEmpty()) {
                    reff = FirebaseDatabase.getInstance().getReference().child("Propertytaxdetails").child(ptino);
                    reff.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Propertytaxdetails info = dataSnapshot.getValue(Propertytaxdetails.class);
                            try {
                                if (ptino.equals(info.getPtino())) {
                                    ptbar.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent(Propertytax.this, Propertytaxdata.class);
                                    startActivity(intent);
                                }
                            } catch (NullPointerException e) {

                                Toast.makeText(Propertytax.this, "Please enter Valid PTI number", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else {
                    Toast.makeText(Propertytax.this, "Please enter PTI number", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //actionbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskbar, menu);
        return true;
    }

    //handle actionbar handle clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {

            Intent intent = new Intent(Propertytax.this,ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
