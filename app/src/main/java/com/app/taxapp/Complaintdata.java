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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Complaintdata extends AppCompatActivity {

    DatabaseReference data;
    SendNotifications notify;
    private String m_Text = "";
    public ArrayList<String> feed = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaintdata);

        notify = new SendNotifications();
        data = FirebaseDatabase.getInstance().getReference().child("SendNotifications");

        final ListView listView = (ListView)findViewById(R.id.clist);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference usersdRef = rootRef.child("Complaintdetails");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String cid = ds.child("complaintid").getValue(String.class);
                    String un = ds.child("category").getValue(String.class);
                    String em = ds.child("problem").getValue(String.class);
                    String mno = ds.child("mno").getValue(String.class);
                    String lm = ds.child("land").getValue(String.class);
                    String dp = ds.child("descp").getValue(String.class);

                    Log.d("TAG",cid);
                    Log.d("TAG", un);
                    Log.d("TAG", em);
                    Log.d("TAG", mno);
                    Log.d("TAG", lm);
                    Log.d("TAG", dp);

                    feed.add("\n"+"Complaint ID :"+ cid +"\n"+"Category :" +un +"\n"+"Problem :" +em +"\n"+"Mobile no :" +mno +"\n"+"Landmark :" +lm +"\n"+"Description :" +dp +"\n"+ "");
                    feed.add("REPLY");

                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Complaintdata.this,android.R.layout.simple_list_item_1,feed);
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
                                final String cid = ds.child("complaintid").getValue(String.class);
                                final String un = ds.child("category").getValue(String.class);
                                final String em = ds.child("problem").getValue(String.class);

                                //final Button replybtn = feed.get(position);
                                final AlertDialog.Builder builder = new AlertDialog.Builder(Complaintdata.this);
                                builder.setTitle("Send Reply");
                                builder.setMessage("Complaint ID :"+cid +"\n"+"Category :" +un +"\n"+"Problem :" +em+"\n");

                                // Set up the input
                                final EditText input = new EditText(Complaintdata.this);
                                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                                builder.setView(input);

                                // Set up the buttons
                                builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int listposition) {
                                        m_Text = input.getText().toString().trim();
                                        String compid = listPosition;

                                        notify.setComid(cid);
                                        notify.setCategory(un);
                                        notify.setPrblm(em);
                                        notify.setMessage(m_Text);

                                        data.child(cid).setValue(notify);
                                        Toast.makeText(Complaintdata.this,"Reply Sent Successfully",Toast.LENGTH_LONG).show();
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
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Complaintdata.this,android.R.layout.simple_list_item_1,feed);
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

