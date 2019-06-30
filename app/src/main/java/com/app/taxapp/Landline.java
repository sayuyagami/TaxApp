package com.app.taxapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Landline extends Settings {
    private final int PICK_CONTACT = 1;
    TextInputEditText mobileno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landline);

        ImageView airtel = (ImageView) findViewById(R.id.airtel);
        ImageView bsnl = (ImageView) findViewById(R.id.bsnl);
        ImageView tata = (ImageView) findViewById(R.id.tata);
        mobileno = findViewById(R.id.mobileno);

        Button button = (Button) findViewById(R.id.pickcontact);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Landline.this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, PICK_CONTACT);
                }else {
                    requestContactsPermission();
                }
            }
        });

        airtel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Landline.this,"Airtel selected",Toast.LENGTH_LONG).show();
            }
        });

        bsnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Landline.this,"BSNL selected",Toast.LENGTH_LONG).show();
            }
        });

        tata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Landline.this,"TATA Teleservices Limited selected",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override public void onActivityResult(int reqCode, int resultCode, Intent data){
        super.onActivityResult(reqCode, resultCode, data);

        switch(reqCode)
        {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK)
                {
                    Uri contactData = data.getData();
                    Cursor c = managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst())
                    {
                        String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                        String hasPhone =
                                c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)).trim();

                        if (hasPhone.equalsIgnoreCase("1"))
                        {
                            //ContentResolver cr = getContentResolver();
                            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                           /* @SuppressLint("Recycle") Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);*/
                            phones.moveToFirst();
                            String cNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            //Toast.makeText(getApplicationContext(), cNumber, Toast.LENGTH_SHORT).show();
                            mobileno.setText(cNumber);
                        }
                    }
                }
        }
    }

    private void requestContactsPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)){

            ActivityCompat.requestPermissions(Landline.this,new String[] {Manifest.permission.READ_CONTACTS},PICK_CONTACT);
           /*new AlertDialog.Builder(this).setTitle("Permission needed").setMessage("This permission is needed to access your contacts")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Landline.this,new String[] {Manifest.permission.READ_CONTACTS},PICK_CONTACT);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else {
            ActivityCompat.requestPermissions(Landline.this,new String[] {Manifest.permission.READ_CONTACTS},PICK_CONTACT);*/
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PICK_CONTACT){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }else {
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_LONG).show();
            }
        }
    }

    //actionbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskbar, menu);
        return true;
    }

    //handle actionbar handle clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {

            Intent intent = new Intent(Landline.this,ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
