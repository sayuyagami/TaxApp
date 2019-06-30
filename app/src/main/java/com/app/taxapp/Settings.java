package com.app.taxapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Settings extends AppCompatActivity {
    RadioButton radioButton;
    RadioGroup radioGroup;
    Button change;
    RadioButton toeng,totel,tohin;
    DatabaseReference reff,refer;
    Registered members;
    FirebaseUser user;
    String uid;
    TextInputEditText mobilenum;
    TextInputEditText email;
    Button update;

    public static String regnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        radioGroup = findViewById(R.id.radioGroup);
        email = findViewById(R.id.email);
        mobilenum = findViewById(R.id.mobilenum);
        update = (Button)findViewById(R.id.update);
        change = (Button)findViewById(R.id.change);

        toeng = (RadioButton)findViewById(R.id.eng);
        totel = (RadioButton)findViewById(R.id.telu);
        tohin = (RadioButton)findViewById(R.id.hin);

        members=new Registered();

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = mobilenum.toString().trim();

                if (num.isEmpty() || num.length() > 10) {
                    mobilenum.setError("Valid number is required");
                    mobilenum.requestFocus();
                }
            }
        });

        SharedPreferences share = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        final String userno = share.getString("mobileno","");
        //regnum = Activity2.num;
        reff= FirebaseDatabase.getInstance().getReference().child("Registered").child(userno);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Registered data = dataSnapshot.getValue(Registered.class);
                mobilenum.setText(data.getPhonenum());
                email.setText(data.getMailid());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refer = FirebaseDatabase.getInstance().getReference().child("Registered");
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from textfields
                String phn = mobilenum.getText().toString().trim();
                String mail = email.getText().toString().trim();

                if (!phn.isEmpty() && !mail.isEmpty()) {

                    //Update data
                    members.setPhonenum(phn);
                    members.setMailid(mail);

                    refer.child(userno).setValue(members);
                    Toast.makeText(Settings.this, "Updated Successfully !!", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(Settings.this,"Please enter your Email address",Toast.LENGTH_LONG).show();

                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                Intent intent = new Intent(Settings.this,ProfileActivity.class);
                startActivity(intent);
                Toast.makeText(Settings.this," updated language "+ radioButton.getText(),Toast.LENGTH_LONG).show();
            }
        });

        toeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViews("en");
            }
        });

        totel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViews("te");
                RadioButton checkedRadioButton = radioGroup.findViewById(R.id.telu);
                checkedRadioButton.setChecked(true);
            }
        });

        tohin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViews("hi");
                RadioButton checkedRadioButton = radioGroup.findViewById(R.id.hin);
                checkedRadioButton.setChecked(true);
            }
        });
        setTitle(getString(R.string.main_activity_title));
    }

    public void check(View view){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        //boolean checked = ((RadioButton) view).isChecked();
        radioButton.isChecked();
       /* String text = (String) radioButton.getText();
        if(text == "English") {
            updateViews("en");
        }else if(text == "తెలుగు") {
            updateViews("te");
        }else if(text == "हिंदी"){
            updateViews("hi");
        }*/
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    private void  updateViews(String languageCode){
        Context context = LocaleHelper.setLocale(Settings.this, languageCode);
        Resources resources = context.getResources();
        setTitle(getString(R.string.main_activity_title));
    }

}
