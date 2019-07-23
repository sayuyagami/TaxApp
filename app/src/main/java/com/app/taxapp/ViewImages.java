package com.app.taxapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewImages extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    private ProgressBar progressBar;

    DatabaseReference mDatabaseRef;
    public static List<Complaintdetails> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_images);

        mRecyclerView=findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ViewImages.this));
        mAdapter=new ImageAdapter(ViewImages.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);

        progressBar=findViewById(R.id.progress_circular);

        mUploads= new ArrayList<>();

        mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("Complaintdetails");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Complaintdetails upload=dataSnapshot.getValue(Complaintdetails.class);

                    /*String cid = postSnapshot.child("complaintid").getValue(String.class);
                    String apl = postSnapshot.child("addpicleft").getValue(String.class);
                    String ap =postSnapshot.child("addpic").getValue(String.class);
                    String apr = postSnapshot.child("addpicright").getValue(String.class);

                    mUploads.add(cid);
                    mUploads.add(apl);
                    mUploads.add(ap);
                    mUploads.add(apr);*/

                mUploads.add(upload);
                Toast.makeText(ViewImages.this,"Data : "+mUploads.add(upload),Toast.LENGTH_LONG).show();

                mAdapter=new ImageAdapter(ViewImages.this,mUploads);
                mRecyclerView.setAdapter(mAdapter);
                //Toast.makeText(ViewImages.this,"details: "+mUploads.get(1),Toast.LENGTH_LONG).show();
                Toast.makeText(ViewImages.this,"details: "+upload.getDescp(),Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ViewImages.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}