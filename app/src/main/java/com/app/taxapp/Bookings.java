package com.app.taxapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class Bookings extends Settings {
    ArrayList<String> items1 = new ArrayList<>();

    ArrayList<String> items2 = new ArrayList<>();

    SpinnerDialog srcspinnerDialog;
    SpinnerDialog destspinnerDialog;
    Button srcbtn;
    Button destbtn;
    Button datebtn;
    DatePickerDialog datePickerDialog;
    Calendar calender;
    int year;
    int month;
    int dayofMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        datebtn = (Button)findViewById(R.id.datebtn);
        srcbtn = (Button)findViewById(R.id.source);
        destbtn = (Button)findViewById(R.id.dest);

        initItems1();
        srcspinnerDialog = new SpinnerDialog(Bookings.this,items1,"Select Source city");
        srcspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item1, int position) {
                srcbtn.setText(item1);
            }
        });

        initItems2();
        destspinnerDialog = new SpinnerDialog(Bookings.this,items2,"Select Destination city");
        destspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position) {
                destbtn.setText(item2);
            }
        });


        srcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                srcspinnerDialog.showSpinerDialog();
            }
        });

        destbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destspinnerDialog.showSpinerDialog();
            }
        });

        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calender = Calendar.getInstance();
                year = calender.get(Calendar.YEAR);
                month = calender.get(Calendar.MONTH);
                dayofMonth = calender.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Bookings.this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        datebtn.setText(day + " / " +(month+1) + " / " + year);
                    }
                }, year,month,dayofMonth);
                datePickerDialog.show();
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

            Intent intent = new Intent(Bookings.this,ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initItems1() {
            items1.add("Hyderabad");
            items1.add("Warangal");
            items1.add("Nizamabad");
            items1.add("Khammam");
            items1.add("Karimnagar");
            items1.add("Ramagundam");
            items1.add("Mahabubnagar");
            items1.add("Nalgonda");
            items1.add("Adilabad");
            items1.add("Suryapet");
    }

    private void initItems2() {
        items2.add("Hyderabad");
        items2.add("Warangal");
        items2.add("Nizamabad");
        items2.add("Khammam");
        items2.add("Karimnagar");
        items2.add("Ramagundam");
        items2.add("Mahabubnagar");
        items2.add("Nalgonda");
        items2.add("Adilabad");
        items2.add("Suryapet");
    }
}
