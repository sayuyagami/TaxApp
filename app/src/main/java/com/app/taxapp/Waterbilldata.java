package com.app.taxapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Waterbilldata extends AppCompatActivity {

    TextView a,b,c,d,e,f,g;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterbilldata);

        a = (TextView)findViewById(R.id.a);
        b = (TextView)findViewById(R.id.b);
        c = (TextView)findViewById(R.id.c);
        d = (TextView)findViewById(R.id.d);
        e = (TextView)findViewById(R.id.e);
        f = (TextView)findViewById(R.id.f);
        g = (TextView)findViewById(R.id.g);

        reff = FirebaseDatabase.getInstance().getReference().child("Waterbilldetails").child(Waterbill.canno);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Waterbilldetails info = dataSnapshot.getValue(Waterbilldetails.class);

                a.setText(info.getCn());
                b.setText(info.getAddress());
                c.setText(info.getMobileno());
                d.setText(info.getCan());
                e.setText(info.getCategory());
                f.setText(info.getDue());
                g.setText(info.getTotal());

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

            Intent intent = new Intent(Waterbilldata.this,Waterbill.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
