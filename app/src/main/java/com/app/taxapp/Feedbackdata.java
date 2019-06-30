package com.app.taxapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feedbackdata extends AppCompatActivity {

    public ArrayList<String> feed = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackdata);

        //data = FirebaseDatabase.getInstance().getReference("FeedbackMembers");
        //data = database.getReference("FeedbackMembers");

        final ListView listView = (ListView)findViewById(R.id.listview);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersdRef = rootRef.child("FeedbackMembers");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String un = ds.child("name").getValue(String.class);
                    String em = ds.child("email").getValue(String.class);
                    String sub = ds.child("subject").getValue(String.class);
                    Log.d("TAG", un);
                    Log.d("TAG", em);
                    Log.d("TAG", sub);
                    feed.add("\n"+"Name :" +un + "\n" +"Email :" +em + "\n" + "Subject :" +sub +"\n"+ "");
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Feedbackdata.this,android.R.layout.simple_list_item_1,feed);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        usersdRef.addListenerForSingleValueEvent(eventListener);
    }
}
