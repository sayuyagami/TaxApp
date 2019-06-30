package com.app.taxapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Propertytaxdata extends AppCompatActivity {

    TextView a,b,c,d,e,f,g,h;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertytaxdata);

        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c= findViewById(R.id.c);
        d = findViewById(R.id.d);
        e = findViewById(R.id.e);
        f = findViewById(R.id.f);
        g = findViewById(R.id.g);
        h = findViewById(R.id.h);

        reff = FirebaseDatabase.getInstance().getReference().child("Propertytaxdetails").child(Propertytax.ptino);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Propertytaxdetails data = dataSnapshot.getValue(Propertytaxdetails.class);

                a.setText(data.getCn());
                b.setText(data.getAddress());
                c.setText(data.getPtino());
                d.setText(data.getLocality());
                e.setText(data.getCircle());
                f.setText(data.getTaxdue());
                g.setText(data.getCurrent());
                h.setText(data.getTotal());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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

            Intent intent = new Intent(Propertytaxdata.this,Propertytax.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
