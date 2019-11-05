package com.app.taxapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddBookings extends AppCompatActivity {

    DatabaseReference data;
    SendReply reply;
    ListView listView;
    private String m_Text = "";
    public ArrayList<String> feed = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bookings);

        reply = new SendReply();
        data = FirebaseDatabase.getInstance().getReference().child("SendReply");

        listView = findViewById(R.id.clist);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference usersdRef = rootRef.child("Tankerdetails");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String cid = ds.child("wardnum").getValue(String.class);
                    String un = ds.child("name").getValue(String.class);
                    String em = ds.child("phn").getValue(String.class);
                    String mno = ds.child("address").getValue(String.class);
                    String lm = ds.child("date").getValue(String.class);

                    Log.d("TAG",cid);
                    Log.d("TAG", un);
                    Log.d("TAG", em);
                    Log.d("TAG", mno);
                    Log.d("TAG", lm);

                    feed.add("\n"+"Ward No :"+ cid +"\n"+"Name :" +un +"\n"+"Mobile no:" +em +"\n"+"Address :" +mno +"\n"+"Date :" +lm +"\n"+ "");
                    feed.add("REPLY");

                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddBookings.this,android.R.layout.simple_list_item_1,feed);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        usersdRef.addListenerForSingleValueEvent(eventListener);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i,
                                    final long id) {
                final String listPosition = feed.get(i);
                if (i%2 != 0){
                    ValueEventListener eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                                final String cid = ds.child("wardnum").getValue(String.class);
                                final String un = ds.child("name").getValue(String.class);
                                final String mn = ds.child("phn").getValue(String.class);
                                final String em = ds.child("date").getValue(String.class);

                                //final Button replybtn = feed.get(position);
                                final AlertDialog.Builder builder = new AlertDialog.Builder(AddBookings.this);
                                /*final EditText check = new EditText(AddBookings.this);
                                check.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                                check.setText("set status");*/
                                builder.setTitle("Send Reply");
                                builder.setMessage(cid +"\n"+"Consumer Name :" +un +"\n");

                                // Set up the input

                                final EditText input = new EditText(AddBookings.this);
                                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                                input.setText("We have received your request on "+em +"\n"+"We will be there in 24 mins" +"\n"+"if there are any issues please feel free to contact us!!");
                                //builder.setMessage("Status :" +check+"\n");
                                builder.setView(input);

                                // Set up the buttons
                                builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int listposition) {
                                        //String status = check.getText().toString().trim();
                                        m_Text = input.getText().toString().trim();

                                        reply.setWardnumber(cid);
                                        reply.setCustomername(un);
                                        reply.setCurdate(em);
                                        reply.setMno(mn);
                                        reply.setStatus("Request accepted");
                                        reply.setMsg(m_Text);

                                        data.child(mn).setValue(reply);
                                        Toast.makeText(AddBookings.this,"Reply Sent Successfully",Toast.LENGTH_LONG).show();
                                    }
                                });
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                                builder.show();

                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddBookings.this,android.R.layout.simple_list_item_1,feed);
                            listView.setAdapter(arrayAdapter);
                            arrayAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    };
                    usersdRef.addListenerForSingleValueEvent(eventListener);
                }
            }
        });
    }
}
