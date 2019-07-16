package com.app.taxapp;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Regtanker extends AppCompatActivity {
    TextInputEditText cn,address,mno;
    DatabaseReference data;
    Tankerdetails info;
    int year;
    int month;
    int dayofMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regtanker);

        final Spinner ward = findViewById(R.id.ward);
        final TextView street = findViewById(R.id.street);
        cn = findViewById(R.id.cn);
        address = findViewById(R.id.address);
        mno = findViewById(R.id.mno);
        final Button date = findViewById(R.id.date);
        Button submit = findViewById(R.id.submit);

        info = new Tankerdetails();
        data = FirebaseDatabase.getInstance().getReference().child("Tankerdetails");

        final List<String> power = new ArrayList<String>();
        power.add("Select Ward number");
        for (int i=1;i <= 24; i++){
            power.add(""+i);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,power);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ward.setAdapter(arrayAdapter);
        ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ward.setSelection(i);

                if (i != 0 ) {
                    street.setText("WARD NO :" +i);
                }else {
                    street.setText("Select Street");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calender = Calendar.getInstance();
                year = calender.get(Calendar.YEAR);
                month = calender.get(Calendar.MONTH);
                dayofMonth = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Regtanker.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + " / " + (month + 1) + " / " + year);
                            }
                        }, year, month, dayofMonth);
                datePickerDialog.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = street.getText().toString().trim();
                String name = cn.getText().toString().trim();
                String phn = mno.getText().toString().trim();
                String addr = address.getText().toString().trim();
                String dt = date.getText().toString().trim();

                if (!str.isEmpty() && !name.isEmpty() && !phn.isEmpty() && !addr.isEmpty() && !dt.isEmpty()){
                    info.setWardnum(str);
                    info.setName(name);
                    info.setPhn(phn);
                    info.setAddress(addr);
                    info.setDate(dt);

                    data.child(phn).setValue(info);
                    Toast.makeText(Regtanker.this,"Sent Request",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(Regtanker.this,"Please fill all the fields",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
