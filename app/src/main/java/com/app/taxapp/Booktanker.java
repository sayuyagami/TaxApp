package com.app.taxapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Booktanker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booktanker);

        ImageView bt = findViewById(R.id.bt);
        ImageView cwt = findViewById(R.id.cwt);
        TextView source = findViewById(R.id.source);
        TextView dest = findViewById(R.id.dest);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booktanker.this,Regtanker.class);
                startActivity(intent);
            }
        });

        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booktanker.this,Regtanker.class);
                startActivity(intent);
            }
        });

        cwt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booktanker.this,Check_Regtanker.class);
                startActivity(intent);
            }
        });

        dest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booktanker.this,Check_Regtanker.class);
                startActivity(intent);
            }
        });

    }
}
