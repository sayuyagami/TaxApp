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

public class AddWbill extends AppCompatActivity {
    TextInputEditText cn,address,mn,can,cat,amtdue,total;
    Button store;
    DatabaseReference reff;
    Waterbilldetails details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wbill);

        cn = findViewById(R.id.cn);
        address = findViewById(R.id.address);
        mn = findViewById(R.id.mn);
        can = findViewById(R.id.can);
        cat = findViewById(R.id.cat);
        amtdue = findViewById(R.id.amtdue);
        total = findViewById(R.id.total);
        store = (Button)findViewById(R.id.store);
        details = new Waterbilldetails();
        reff = FirebaseDatabase.getInstance().getReference().child("Waterbilldetails");

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wcn = cn.getText().toString().trim();
                String add = address.getText().toString().trim();
                String mno = mn.getText().toString().trim();
                String cano = can.getText().toString().trim();
                String due = amtdue.getText().toString().trim();
                String category = cat.getText().toString().trim();
                String totalamt = total.getText().toString().trim();

                if (!wcn.isEmpty() && !add.isEmpty() && !mno.isEmpty() && !cano.isEmpty() && !due.isEmpty() && !category.isEmpty() && !totalamt.isEmpty()){

                    details.setCn(wcn);
                    details.setAddress(add);
                    details.setMobileno(mno);
                    details.setCan(cano);
                    details.setCategory(category);
                    details.setDue(due);
                    details.setTotal(totalamt);

                    reff.child(cano).setValue(details);
                    Toast.makeText(AddWbill.this,"Bill Details Submitted",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(AddWbill.this,"Please fill all the fields",Toast.LENGTH_LONG).show();
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

            Intent intent = new Intent(AddWbill.this,Adminhome.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
