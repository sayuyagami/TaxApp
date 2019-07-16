package com.app.taxapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class ProfileActivity extends Settings {

    ImageView bulbbtn;
    ImageView dropbtn;
    ImageView proptaxbtn;
    ImageView dthbtn;
    ImageView complaintbtn;
    ImageView duebtn;
    ImageView tankerbtn;
    ImageView landlinebtn;
    ImageView bookbtn;
    ImageView callsbtn;
    SharedPreferences num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final String uid;

        /*usermn = (TextView)findViewById(R.id.usermn);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        data = FirebaseDatabase.getInstance().getReference();

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String user_number = dataSnapshot.child(uid).child("number").getValue(String.class);
                usermn.setText(user_number);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"Network Error",Toast.LENGTH_SHORT).show();

            }
        });*/

        bulbbtn = (ImageView)findViewById(R.id.bulbbtn);
        dropbtn = (ImageView)findViewById(R.id.dropbtn);
        proptaxbtn = (ImageView)findViewById(R.id.proptaxbtn);
        dthbtn = (ImageView)findViewById(R.id.dthbtn);
        complaintbtn = (ImageView)findViewById(R.id.complaintbtn);
        duebtn = (ImageView)findViewById(R.id.duebtn);
        //wifibtn = (ImageView)findViewById(R.id.wifibtn);
        tankerbtn = (ImageView)findViewById(R.id.tankerbtn);
        landlinebtn = (ImageView)findViewById(R.id.landlinebtn);
        bookbtn = (ImageView)findViewById(R.id.bookbtn);
        callsbtn = (ImageView)findViewById(R.id.callsbtn);

        TextView item1 = (TextView)findViewById(R.id.item1);
        TextView item2 = (TextView)findViewById(R.id.item2);
        TextView item3 = (TextView)findViewById(R.id.item3);
        TextView item4 = (TextView)findViewById(R.id.item4);
        TextView item5 = (TextView)findViewById(R.id.item5);
        TextView item6 = (TextView)findViewById(R.id.item6);
        TextView item7 = (TextView)findViewById(R.id.item7);
        TextView item8 = (TextView)findViewById(R.id.item8);
        TextView item9 = (TextView)findViewById(R.id.item9);
        TextView item10 = (TextView)findViewById(R.id.item10);

        bulbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulb();
            }
        });
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulb();
            }
        });

        dropbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drop();
            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drop();
            }
        });

        proptaxbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proptax();
            }
        });
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proptax();
            }
        });

        dthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dth();
            }
        });
        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dth();
            }
        });

        complaintbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complaint();
            }
        });
        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complaint();
            }
        });

        duebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                due();
            }
        });
        item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                due();
            }
        });

        /*wifibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifi();
            }
        });
        item7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifi();
            }
        });*/

        tankerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanker();
            }
        });
        item7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanker();
            }
        });

        landlinebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });
        item8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });

        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book();
            }
        });
        item9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book();
            }
        });

        callsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calls();
            }
        });
        item10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calls();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_main, menu);
        menu.getItem(0).getSubMenu().getItem(4).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

       if (id == R.id.setting){
            Intent intent = new Intent(ProfileActivity.this,Settings.class);
            startActivity(intent);
        }

        if (id == R.id.tranhis){
            Intent intent = new Intent(ProfileActivity.this,Transactionhist.class);
            startActivity(intent);
        }

        if (id == R.id.bell){
            Intent intent = new Intent(ProfileActivity.this,Notifications.class);
            startActivity(intent);
        }

        if (id == R.id.feed){
            Intent intent = new Intent(ProfileActivity.this,Feedback.class);
            startActivity(intent);
        }

        if (id == R.id.info){
            Intent intent = new Intent(ProfileActivity.this,Information.class);
            startActivity(intent);
        }

        if (id == R.id.logoutbtn){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(ProfileActivity.this,Register.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            Toast.makeText(this,"Successfully Logged Out",Toast.LENGTH_LONG).show();
            //startActivity(new Intent(this,MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void bulb(){

        Intent intent = new Intent(ProfileActivity.this,Electricbill.class);
        startActivity(intent);
    }

    private void drop(){

        Intent intent = new Intent(ProfileActivity.this,Waterbill.class);
        startActivity(intent);
    }

    private void proptax(){

        Intent intent = new Intent(ProfileActivity.this,Propertytax.class);
        startActivity(intent);
    }

    private void dth(){

        Intent intent = new Intent(ProfileActivity.this,Dthpay.class);
        startActivity(intent);
    }

    private void complaint(){

        Intent intent = new Intent(ProfileActivity.this,Complaints.class);
        startActivity(intent);
    }

    private void due(){

        Intent intent = new Intent(ProfileActivity.this,Checkdues.class);
        startActivity(intent);
    }

    /*private void wifi(){

        Intent intent = new Intent(ProfileActivity.this,Wifi.class);
        startActivity(intent);
    }*/

    private void tanker(){

        Intent intent = new Intent(ProfileActivity.this,Booktanker.class);
        startActivity(intent);
    }

    private void home() {

        Intent intent = new Intent(ProfileActivity.this,Landline.class);
        startActivity(intent);
    }

    private void book(){

        Intent intent = new Intent(ProfileActivity.this,Bookings.class);
        startActivity(intent);
    }

    private void calls(){

        Intent intent = new Intent(ProfileActivity.this,Emergencycalls.class);
        startActivity(intent);
    }

    public void  onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent =new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        ProfileActivity.super.onBackPressed();
                        System.exit(0);
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}


