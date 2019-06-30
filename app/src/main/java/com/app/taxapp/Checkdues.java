package com.app.taxapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Checkdues extends Settings {

    DatabaseReference reff;
    Spinner type;
    int clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkdues);

        type = (Spinner) findViewById(R.id.type);
        final TextInputEditText num = findViewById(R.id.num);
        Button checkbtn = findViewById(R.id.checkbtn);

        List<String> name = new ArrayList<String>();
        boolean add = name.add(getString(R.string.type_dues));
        name.add("Property Tax Dues");
        name.add("Electricity Bill payment Dues");
        name.add("Water Bill payment Dues");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,name);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(arrayAdapter);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type.setSelection(i);
                if (i == 1){
                    clicked = 1;
                    Toast.makeText(Checkdues.this,"Enter PTI number",Toast.LENGTH_LONG).show();
                }else if (i == 2){
                    clicked = 2;
                    Toast.makeText(Checkdues.this,"Enter SC No",Toast.LENGTH_LONG).show();
                }else if (i ==3){
                    clicked = 3;
                    Toast.makeText(Checkdues.this,"Enter CAN number",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pscno = num.getText().toString().trim();
                if(!pscno.isEmpty()){
                    if (clicked == 1 || clicked == 2 || clicked == 3){
                        if (clicked == 1){
                            reff = FirebaseDatabase.getInstance().getReference().child("Propertytaxdetails").child(pscno);
                            reff.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Propertytaxdetails info = dataSnapshot.getValue(Propertytaxdetails.class);
                                    try{
                                        if (pscno.equals(info.getPtino())){

                                            AlertDialog.Builder builder = new AlertDialog.Builder(Checkdues.this);
                                            builder.setTitle("Your Property Tax Due");
                                            builder.setMessage("\n"+"Name :"+info.getCn()+"\n"
                                                    +"PTIN :"+info.getPtino()+"\n"
                                                    +"Locality :"+info.getLocality()+"\n\n"
                                                    +"Total Due: ₹"+info.getTaxdue())
                                                    .setCancelable(false)

                                                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            dialogInterface.cancel();
                                                        }
                                                    });
                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();

                                        }
                                    }catch (NullPointerException e){
                                        Toast.makeText(Checkdues.this,"Please enter Valid number",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }else if (clicked == 2){
                            reff = FirebaseDatabase.getInstance().getReference().child("Electricbilldetails").child(pscno);
                            reff.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Electricbilldetails info = dataSnapshot.getValue(Electricbilldetails.class);
                                    try{
                                        if (pscno.equals(info.getBillscno())){

                                            AlertDialog.Builder builder = new AlertDialog.Builder(Checkdues.this);
                                            builder.setTitle("Electric Bill Details");
                                            builder.setMessage("\n"+"Name :"+info.getCn()+"\n"
                                                    +"SC No :"+info.getBillscno()+"\n"
                                                    +"District :"+info.getDist()+"\n"
                                                    +"ERO :"+info.getEro()+"\n"
                                                    +"Due Date :"+info.getDue()+"\n\n"
                                                    +"Total Due: ₹"+info.getTotal())
                                                    .setCancelable(false)

                                                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            dialogInterface.cancel();
                                                        }
                                                    });
                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();

                                        }
                                    }catch (NullPointerException e){
                                        Toast.makeText(Checkdues.this,"Please enter Valid number",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }else if (clicked == 3){
                            reff = FirebaseDatabase.getInstance().getReference().child("Waterbilldetails").child(pscno);
                            reff.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Waterbilldetails info = dataSnapshot.getValue(Waterbilldetails.class);
                                    try{
                                        if (pscno.equals(info.getCan())){

                                            AlertDialog.Builder builder = new AlertDialog.Builder(Checkdues.this);
                                            builder.setTitle("Your Property Tax Due");
                                            builder.setMessage("\n"+"Name :"+info.getCn()+"\n"
                                                    +"CAN Number :"+info.getCan()+"\n"
                                                    +"Category :"+info.getCategory()+"\n\n"
                                                    +"Total Due: ₹"+info.getDue())
                                                    .setCancelable(false)

                                                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            dialogInterface.cancel();
                                                        }
                                                    });
                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();

                                        }
                                    }catch (NullPointerException e){
                                        Toast.makeText(Checkdues.this,"Please enter Valid number",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                    }else {
                        Toast.makeText(Checkdues.this,"Please Select type",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(Checkdues.this,"Please enter number",Toast.LENGTH_LONG).show();
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

            Intent intent = new Intent(Checkdues.this,ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
