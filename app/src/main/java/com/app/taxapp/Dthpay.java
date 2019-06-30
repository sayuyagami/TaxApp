package com.app.taxapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Dthpay extends Settings {

    Spinner dthop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dthpay);

        dthop = (Spinner)findViewById(R.id.dthop);

        List<String> opt = new ArrayList<String>();
        final boolean add = opt.add(getString(R.string.dthopselect));
        opt.add("AIRTEL TV");
        opt.add("BIG TV");
        opt.add("DISH TV");
        opt.add("SUN DIRECT");
        opt.add("TATASKY TV");
        opt.add("VIDEOCOND2H");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opt);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dthop.setAdapter(arrayAdapter);
        dthop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dthop.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

            Intent intent = new Intent(Dthpay.this,ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
