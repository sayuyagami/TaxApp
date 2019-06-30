package com.app.taxapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Emergencycalls extends Settings {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencycalls);

        ImageView policebtn = (ImageView)findViewById(R.id.policebtn);
        ImageView firebtn = (ImageView)findViewById(R.id.firebtn);
        ImageView ambulancebtn = (ImageView)findViewById(R.id.ambulancebtn);
        ImageView irctcbtn = (ImageView)findViewById(R.id.irctcbtn);
        ImageView childhelpbtn = (ImageView)findViewById(R.id.childhelpbtn);
        ImageView womenhelpbtn = (ImageView)findViewById(R.id.womenhelpbtn);
        ImageView shebtn = (ImageView)findViewById(R.id.shebtn);

        policebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:100"));
                startActivity(intent);

            }
        });

        policebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:100"));
                startActivity(intent);

            }
        });

        firebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:101"));
                startActivity(intent);

            }
        });

        ambulancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:108"));
                startActivity(intent);

            }
        });

        irctcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:139"));
                startActivity(intent);

            }
        });

        childhelpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1098"));
                startActivity(intent);

            }
        });

        womenhelpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1091"));
                startActivity(intent);

            }
        });

        shebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9490617444"));
                startActivity(intent);

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

            Intent intent = new Intent(Emergencycalls.this,ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
