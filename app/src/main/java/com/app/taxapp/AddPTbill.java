package com.app.taxapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPTbill extends AppCompatActivity {
    TextInputEditText cn,address,ptino,locality,circle,taxdue,currenttax,total;
    Button store;
    DatabaseReference reff;
    Propertytaxdetails details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ptbill);

        cn = findViewById(R.id.cn);
        address = findViewById(R.id.address);
        ptino = findViewById(R.id.ptino);
        locality = findViewById(R.id.locality);
        circle = findViewById(R.id.circle);
        taxdue = findViewById(R.id.taxdue);
        currenttax = findViewById(R.id.currenttax);
        total = findViewById(R.id.total);
        store = (Button)findViewById(R.id.store);
        details = new Propertytaxdetails();
        reff = FirebaseDatabase.getInstance().getReference().child("Propertytaxdetails");

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wcn = cn.getText().toString().trim();
                String add = address.getText().toString().trim();
                String ptno = ptino.getText().toString().trim();
                String cano = locality.getText().toString().trim();
                String due = circle.getText().toString().trim();
                String category = taxdue.getText().toString().trim();
                String current = currenttax.getText().toString().trim();
                String totalamt = total.getText().toString().trim();

                if (!wcn.isEmpty() && !add.isEmpty() && !ptno.isEmpty() && !cano.isEmpty() && !due.isEmpty() && !category.isEmpty() && !current.isEmpty() && !totalamt.isEmpty()){

                    details.setCn(wcn);
                    details.setAddress(add);
                    details.setPtino(ptno);
                    details.setLocality(cano);
                    details.setTaxdue(category);
                    details.setCircle(due);
                    details.setCurrent(current);
                    details.setTotal(totalamt);

                    reff.child(ptno).setValue(details);
                    Toast.makeText(AddPTbill.this,"Tax Details Submitted",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(AddPTbill.this,"Please fill all the fields",Toast.LENGTH_LONG).show();
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

            Intent intent = new Intent(AddPTbill.this,Adminhome.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
