package com.app.taxapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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

    DatabaseReference data,usersdRef;
    SendNotifications notify;
    ListView listView;
    private String m_Text = "";
    public ArrayList<String> feed = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaintdata);

        notify = new SendNotifications();
        data = FirebaseDatabase.getInstance().getReference().child("SendNotifications");

        listView = findViewById(R.id.clist);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        usersdRef = rootRef.child("Complaintdetails");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    int cid = ds.child("complaintid").getValue(int.class);
                    String un = ds.child("category").getValue(String.class);
                    String em = ds.child("problem").getValue(String.class);
                    String mno = ds.child("mno").getValue(String.class);
                    String lm = ds.child("land").getValue(String.class);
                    String dp = ds.child("descp").getValue(String.class);

                    if (cid != 0) {
                        Log.d("TAG", String.valueOf(cid));
                        Log.d("TAG", un);
                        Log.d("TAG", em);
                        Log.d("TAG", mno);
                        Log.d("TAG", lm);
                        Log.d("TAG", dp);

                        feed.add("\n" + "Complaint ID :" + cid + "\n" + "Category :" + un + "\n" + "Problem :" + em + "\n" + "Mobile no :" + mno + "\n" + "Landmark :" + lm + "\n" + "Description :" + dp + "\n" + "");
                        feed.add("REPLY");

                    } else {
                        Toast.makeText(Complaintdata.this,"No complaints yet !!",Toast.LENGTH_LONG).show();
                       // feed.add("No complaints yet");
                        }
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
                                final int cid = ds.child("complaintid").getValue(int.class);
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
                                input.setText("Thanks for Contacting us !!"+"\n"+"We will be there in 24 mins,Please send feedback about our work!!");
                                builder.setView(input);

                                // Set up the buttons
                                builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int listposition) {
                                        m_Text = input.getText().toString().trim();
                                        String compid = listPosition;

                                        notify.setComid(Integer.parseInt(String.valueOf(cid)));
                                        notify.setCategory(un);
                                        notify.setPrblm(em);
                                        notify.setStatus("Request accepted");
                                        notify.setMessage(m_Text);

                                        data.child(String.valueOf(cid)).setValue(notify);
                                        Toast.makeText(Complaintdata.this,"Reply Sent Successfully",Toast.LENGTH_LONG).show();
                                        usersdRef.child(String.valueOf(cid)).setValue(null);
                                        recreate();

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
                }/*else {
                    Intent intent = new Intent(Complaintdata.this,ViewImages.class);
                    startActivity(intent);
                }*/
            }
        });
    }
}

