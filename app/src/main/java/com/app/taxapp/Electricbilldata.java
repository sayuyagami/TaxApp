package com.app.taxapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Electricbilldata extends AppCompatActivity {

    TextView a,b,c,d,e,f,g,h,i,j,k;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricbilldata);

        a=(TextView) findViewById(R.id.a);
        b=(TextView) findViewById(R.id.b);
        c=(TextView) findViewById(R.id.c);
        d=(TextView) findViewById(R.id.d);
        e=(TextView) findViewById(R.id.e);
        f=(TextView) findViewById(R.id.f);
        g=(TextView) findViewById(R.id.g);
        h=(TextView) findViewById(R.id.h);
        i=(TextView) findViewById(R.id.i);
        j=(TextView) findViewById(R.id.j);
        k=(TextView) findViewById(R.id.k);

        reff = FirebaseDatabase.getInstance().getReference().child("Electricbilldetails").child(Electricbill.sc);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Electricbilldetails data = dataSnapshot.getValue(Electricbilldetails.class);
                a.setText(data.getCn());
                b.setText(data.getAddress());
                c.setText(data.getBillno());
                d.setText(data.getBilldate());
                e.setText(data.getUscno());
                f.setText(data.getBillscno());
                g.setText(data.getDist());
                h.setText(data.getEro());
                i.setText(data.getDue());
                j.setText(data.getCharge());
                k.setText(data.getTotal());
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

            Intent intent = new Intent(Electricbilldata.this,Electricbill.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
