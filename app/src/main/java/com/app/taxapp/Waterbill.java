package com.app.taxapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Waterbill extends Settings {
    boolean clicked = false;

    EditText can;
    DatabaseReference reff;
    ProgressBar wbar;

    public static String canno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterbill);

        ImageView hmw = (ImageView)findViewById(R.id.hmw);
        can = (EditText)findViewById(R.id.can);
        wbar = (ProgressBar)findViewById(R.id.wbar);
        Button submitbtn2 = (Button)findViewById(R.id.submitbtn2);
        //TextView sample = (TextView)findViewById(R.id.electricsample);

        hmw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = true;
                Toast.makeText(Waterbill.this,"HMWSSB selected",Toast.LENGTH_LONG).show();
            }
        });

        submitbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked){
                    canno = can.getText().toString().trim();

                    if (!canno.isEmpty()){
                        reff = FirebaseDatabase.getInstance().getReference().child("Waterbilldetails").child(canno);
                        reff.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Waterbilldetails data =dataSnapshot.getValue(Waterbilldetails.class);

                                try {
                                    if (canno.equals(Objects.requireNonNull(data).getCan())) {
                                        wbar.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(Waterbill.this, Waterbilldata.class);
                                        startActivity(intent);
                                    }
                                } catch (NullPointerException e) {
                                    Toast.makeText(Waterbill.this,"No data available",Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }else {
                        Toast.makeText(Waterbill.this,"Please enter CAN number",Toast.LENGTH_LONG).show();
                    }

                }else {

                    Toast.makeText(Waterbill.this,"Please select organisation",Toast.LENGTH_LONG).show();

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

            Intent intent = new Intent(Waterbill.this,ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
