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

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class Regtanker extends AppCompatActivity {
    TextInputEditText cn,address,mno;
    DatabaseReference data;
    Tankerdetails info;
    SpinnerDialog wardspinnerDialog;
    ArrayList<String> type1 = new ArrayList<>();
    int year;
    int month;
    int dayofMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regtanker);

        final Button ward = findViewById(R.id.ward);
        final TextView street = findViewById(R.id.street);
        cn = findViewById(R.id.cn);
        address = findViewById(R.id.address);
        mno = findViewById(R.id.mno);
        final Button date = findViewById(R.id.date);
        Button submit = findViewById(R.id.submit);

        info = new Tankerdetails();
        data = FirebaseDatabase.getInstance().getReference().child("Tankerdetails");

        ward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initItems1();
                wardspinnerDialog = new SpinnerDialog(Regtanker.this, type1, "Select Street");
                wardspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(final String type1, int position) {
                        if (!type1.isEmpty()){
                            street.setVisibility(View.VISIBLE);
                            street.setText(type1);
                        }
                    }
                });
                wardspinnerDialog.showSpinerDialog();
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

    public void initItems1(){
        type1.add("A C GUARDS");
        type1.add("AZAMABAD");
        type1.add("BANJARA HILLS");
        type1.add("BOWENPALLY");
        type1.add("CHAMPAPET");
        type1.add("CHANCHALGUDA");
        type1.add("GACHIBOWLI");
        type1.add("GAGANPAHAD");
        type1.add("GREENLANDS");
        type1.add("HABSIGUDA");
        type1.add("HAYATNAGAR");
        type1.add("IBRAHIMBAGH");
        type1.add("IBRAHIMPATNAM");
        type1.add("INDIRA PARK");
        type1.add("JEEDIMETLA");
        type1.add("KANDUKUR");
        type1.add("KEESARA");
        type1.add("KONDAPUR");
        type1.add("KUKATPALLY");
        type1.add("MALKAJGIRI");
        type1.add("MEDCHAL");
        type1.add("MINT COMPOUND");
        type1.add("PARADISE");
        type1.add("QUTBULLAHPUR");
        type1.add("RETHIBOWLI");
        type1.add("RP NILAYAM");
        type1.add("SAINIKPURI");
        type1.add("SALAR JUNG");
        type1.add("SANATH NAGAR");
        type1.add("SAROORNAGAR");
        type1.add("SHADNAGAR");
        type1.add("SITHAPHALMANDI");
        type1.add("SULTAN BAZAR");
    }
}

