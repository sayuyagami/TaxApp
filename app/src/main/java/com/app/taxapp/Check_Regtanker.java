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

public class Check_Regtanker extends AppCompatActivity {

    public ArrayList<String> msg = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check__regtanker);

        final ListView listView = (ListView)findViewById(R.id.notify);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersdRef = rootRef.child("SendReply");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String cid = ds.child("wardnumber").getValue(String.class).trim();
                    String cat = ds.child("customername").getValue(String.class).trim();
                    String prblm = ds.child("curdate").getValue(String.class).trim();
                    String stat = ds.child("status").getValue(String.class).trim();
                    String un = ds.child("msg").getValue(String.class).trim();

                    Log.d("TAG", cid);
                    Log.d("TAG", cat);
                    Log.d("TAG", prblm);
                    Log.d("TAG", stat);
                    Log.d("TAG", un);

                    msg.add("\n"+"Name :" +cat+"\n"+cid+"\n"+"Status :"+ stat+"\n"+"Reply :"+un+"\n");

                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Check_Regtanker.this,android.R.layout.simple_list_item_1,msg);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        usersdRef.addListenerForSingleValueEvent(eventListener);
    }
}

