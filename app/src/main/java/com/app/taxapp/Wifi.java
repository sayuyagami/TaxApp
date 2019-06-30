package com.app.taxapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Wifi extends Settings {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        ImageView act = (ImageView)findViewById(R.id.act);
        ImageView digisol = (ImageView)findViewById(R.id.digisol);
        ImageView hathway = (ImageView)findViewById(R.id.hathway);

        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Wifi.this,"ACT provider selected",Toast.LENGTH_LONG).show();
            }
        });

        digisol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Wifi.this,"DIGISOL selected",Toast.LENGTH_LONG).show();
            }
        });

        hathway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Wifi.this,"Hathway selected",Toast.LENGTH_LONG).show();
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

            Intent intent = new Intent(Wifi.this,ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
