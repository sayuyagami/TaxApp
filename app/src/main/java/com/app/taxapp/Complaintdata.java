package com.app.taxapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Complaintdata extends AppCompatActivity {

    public ArrayList<String> feed = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaintdata);

        //data = FirebaseDatabase.getInstance().getReference("FeedbackMembers");
        //data = database.getReference("FeedbackMembers");

        final ListView listView = (ListView)findViewById(R.id.clist);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersdRef = rootRef.child("Complaintdetails");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String un = ds.child("category").getValue(String.class);
                    String em = ds.child("problem").getValue(String.class);
                    String mno = ds.child("mno").getValue(String.class);
                    String lm = ds.child("land").getValue(String.class);
                    String dp = ds.child("descp").getValue(String.class);

                    Log.d("TAG", un);
                    Log.d("TAG", em);
                    Log.d("TAG", mno);
                    Log.d("TAG", lm);
                    Log.d("TAG", dp);

                    feed.add("\n"+"Category :" +un +"\n"+"Problem :" +em +"\n"+"Mobile no :" +mno +"\n"+"Landmark :" +lm +"\n"+"Description :" +dp +"\n"+ "");

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

