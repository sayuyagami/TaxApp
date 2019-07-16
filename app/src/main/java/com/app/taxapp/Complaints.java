package com.app.taxapp;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

import static java.lang.Integer.parseInt;

public class Complaints extends Settings {

    DataSnapshot ds;
    UploadTask.TaskSnapshot ts1,ts2,ts3;
    EditText complaintid;
    SpinnerDialog categoryspinnerDialog,prblmspinnerDialog;
    TextInputEditText userno,landmark,descp;
    Button complaintsubmit,category,problem;
    ImageView addpicleft,addpic,addpicright;

    Integer REQUEST_CAMERA=1;
    private final int CODE_IMG_GALLERY = 1;
    private final int CODE_MULTIPLE_IMG_GALLERY = 3;
    private static final int CAMERA_REQUEST_CODE = 200;

    DatabaseReference reff;
    StorageReference storagereff;

    StorageTask stask;
    Uri imguril,imguri,imgurir;
    Complaintdetails details;

    String cameraPermission[];
    String storagePermission[];

    public ArrayList<String> msg = new ArrayList<>();

    ArrayList<String> type1 = new ArrayList<>();
    ArrayList<String> type2 = new ArrayList<>();
    ArrayList<String> type3 = new ArrayList<>();
    ArrayList<String> type4 = new ArrayList<>();
    ArrayList<String> type5 = new ArrayList<>();
    ArrayList<String> type6 = new ArrayList<>();
    ArrayList<String> type7 = new ArrayList<>();
    ArrayList<String> type8 = new ArrayList<>();
    ArrayList<String> type9 = new ArrayList<>();
    ArrayList<String> type10 = new ArrayList<>();
    ArrayList<String> type11 = new ArrayList<>();
    ArrayList<String> type12 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        details = new Complaintdetails();
        complaintid = findViewById(R.id.complaintid);
        category = (Button) findViewById(R.id.category);
        problem = (Button) findViewById(R.id.problem);
        userno = findViewById(R.id.userno);
        landmark = findViewById(R.id.landmark);
        descp = findViewById(R.id.descp);
        complaintsubmit = (Button)findViewById(R.id.complaintsubmit);

        SharedPreferences share = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        final String mno = share.getString("mobileno","");

        userno.setText(mno);
        //location = (RadioButton)findViewById(R.id.location);
        addpicleft = (ImageView) findViewById(R.id.addpicleft);
        addpic = (ImageView) findViewById(R.id.addpic);
        addpicright = (ImageView) findViewById(R.id.addpicright);

        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        addpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryspinnerDialog.showSpinerDialog();
            }
        });

        initItems1();
        categoryspinnerDialog = new SpinnerDialog(Complaints.this, type1, "Select Grievance Type");
        categoryspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(final String type1, int position) {
                category.setText(type1);
                //Toast.makeText(Complaints.this,"Selected" +type,Toast.LENGTH_LONG).show();

                problem.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){
                        if (type1.isEmpty()) {
                            Toast.makeText(Complaints.this, "Please select the category", Toast.LENGTH_LONG).show();
                        }else {
                            prblmspinnerDialog.showSpinerDialog();
                        }
                    }
                });

                switch (type1) {
                    case "Engineering":
                        type2.clear();
                        initItems2();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type2, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type2, int position) {
                                problem.setText(type2);
                            }
                        });
                        break;
                    case "Electrical":
                        type3.clear();
                        initItems3();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type3, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type3, int position) {
                                problem.setText(type3);
                            }
                        });
                        break;
                    case "Health and Sanitation":
                        type4.clear();
                        initItems4();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Swachhata":
                        type5.clear();
                        initItems5();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type5, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type5, int position) {
                                problem.setText(type5);
                            }
                        });
                        break;
                    case "Entomology":
                        initItems5();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Veterinary":
                        initItems6();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Town Planning":
                        initItems7();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Urban Biodiversity":
                        initItems8();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Advertisement":
                        initItems9();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Estates":
                        initItems10();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Sports":
                        initItems11();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Urban Community Development":
                        initItems12();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Revenue(Property Tax)":
                        initItems13();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Elections":
                        initItems14();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Information Technology":
                        initItems15();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Land Acquisition":
                        initItems16();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Parking":
                        initItems17();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Fire Prevention Wing":
                        initItems18();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                    case "Corruption":
                        initItems19();
                        prblmspinnerDialog = new SpinnerDialog(Complaints.this, type4, "Select your problem");
                        prblmspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String type4, int position) {
                                problem.setText(type4);
                            }
                        });
                        break;
                }
            }

        });

        /*final String cid = ds.child("complaintid").getValue(String.class);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String cid = ds.child("complaintid").getValue(String.class);
                    Log.d("TAG", cid);
                    //int idnum = Integer.parseInt(cid) + 1;
                    complaintid.setText(cid + 1);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        reff.addListenerForSingleValueEvent(eventListener);*/


        //database connection
        reff = FirebaseDatabase.getInstance().getReference().child("Complaintdetails");

        complaintsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stask!= null && stask.isInProgress()){
                    Toast.makeText(Complaints.this,"Upload in progress",Toast.LENGTH_LONG).show();
                }else {
                    uploadimage();
                }
            }
        });
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

            Intent intent = new Intent(Complaints.this,ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void SelectImage(){
        final CharSequence[] items = {"Camera","Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Complaints.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }
                    else {
                        pickCamera();
                    }
                }

                if (which == 1) {
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }
                    else {
                        pickGallery();
                    }
                }
            }
        });

        builder.show();
    }

    private void pickCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_CAMERA);
    }

    private void pickGallery() {
       /* Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent,"Select File"),SELECT_FILE);*/
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select max 3 images"),CODE_MULTIPLE_IMG_GALLERY);
    }

    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE);
    }

    private boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        boolean result1= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    @Override
    public void  onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_CAMERA) {

                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                addpic.setImageBitmap(bmp);
            } else if (requestCode == CODE_MULTIPLE_IMG_GALLERY && resultCode == RESULT_OK) {
                /*Uri selectedImageUri = data.getData();
                addpic.setImageURI(selectedImageUri);*/
                ClipData clipData = data.getClipData();

                if (clipData != null) {
                    try{
                        if (clipData.getItemAt(0) != null && clipData.getItemAt(1) != null && clipData.getItemAt(2) != null){
                            addpicleft.setImageURI(clipData.getItemAt(0).getUri());
                            addpic.setImageURI(clipData.getItemAt(1).getUri());
                            addpicright.setImageURI(clipData.getItemAt(2).getUri());

                            for (int i = 0; i < clipData.getItemCount(); i++) {

                                //ClipData.Item item = clipData.getItemAt(i);
                                imguril = clipData.getItemAt(0).getUri();
                                imguri = clipData.getItemAt(1).getUri();
                                imgurir = clipData.getItemAt(2).getUri();
                                Log.e("MAS IMGS: ", imguri.toString());
                                /*Picasso.with(this).load(imguril).into(addpicleft);
                                Picasso.with(this).load(imguri).into(addpic);
                                Picasso.with(this).load(imgurir).into(addpicright);*/
                            }
                        }
                    } catch (NullPointerException e){
                        Toast.makeText(Complaints.this,"Please select 3 images",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(Complaints.this,"Please select 3 images",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private  String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private  void uploadimage(){
        final String cid = complaintid.getText().toString().trim();
        String cat = category.getText().toString().trim();
        String prblm = problem.getText().toString().trim();
        String uno = userno.getText().toString().trim();
        String lmark = landmark.getText().toString().trim();
        String dcp = descp.getText().toString().trim();

        storagereff = FirebaseStorage.getInstance().getReference(cid);

        if (imguril!= null && imguri!= null && imgurir!= null ){
            final StorageReference file1reff = storagereff.child(System.currentTimeMillis()+"."+getFileExtension(imguril));
            final StorageReference file2reff = storagereff.child(System.currentTimeMillis()+"."+getFileExtension(imguri));
            final StorageReference file3reff = storagereff.child(System.currentTimeMillis()+"."+getFileExtension(imgurir));

            //stask = file2reff.putFile(imguri);

            file1reff.putFile(imguril).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot ts1) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    },500);
                    file1reff.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url1 = ts1.getUploadSessionUri().toString();
                            details.setAddpicleft(url1);
                            reff.child(cid).setValue(details);
                            addpicleft.setImageResource(R.drawable.ic_action_image);
                        }
                    });
                }
            });

            file2reff.putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot ts2) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    },500);
                    file2reff.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url2 = ts2.getUploadSessionUri().toString();
                            details.setAddpic(url2);
                            reff.child(cid).setValue(details);
                            addpic.setImageResource(R.drawable.ic_action_image);
                        }
                    });
                }
            });

            file3reff.putFile(imgurir).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot ts3) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    },500);
                    file3reff.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url3 = ts3.getUploadSessionUri().toString();
                            details.setAddpicright(url3);
                            reff.child(cid).setValue(details);
                            Toast.makeText(Complaints.this, "Complaint Submitted successfully", Toast.LENGTH_LONG).show();
                            addpicright.setImageResource(R.drawable.ic_action_image);
                        }
                    });
                }
            });

            try {
                if (!cid.isEmpty() && !cat.isEmpty() && !prblm.isEmpty() && !uno.isEmpty() && !lmark.isEmpty() && !dcp.isEmpty()){
                    details.setComplaintid(cid);
                    details.setCategory(cat);
                    details.setProblem(prblm);
                    details.setMno(uno);
                    details.setLand(lmark);
                    details.setDescp(dcp);

                    reff.child(cid).setValue(details);
                    //Toast.makeText(Complaints.this,"Complaint Submitted",Toast.LENGTH_LONG).show();
                }
            } catch (NullPointerException e){
                Toast.makeText(Complaints.this,"Please fill all the feilds ",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void initItems1(){
        type1.add("Engineering");
        type1.add("Electrical");
        type1.add("Health and Sanitation");
        type1.add("Swachhata");
        type1.add("Entomology");
        type1.add("Veterinary");
        type1.add("Town Planning");
        type1.add("Urban Biodiversity");
        type1.add("Advertisement");
        type1.add("Estates");
        type1.add("Sports");
        type1.add("Urban Community Development");
        type1.add("Revenue(Property Tax)");
        type1.add("Elections");
        type1.add("Information Technology");
        type1.add("Land Acquisition");
        type1.add("Parking");
        type1.add("Fire Prevention Wing");
        type1.add("Corruption");
    }

    private void initItems2() {
        type2.add("Repairs to Footh Path");
        type2.add("Repairs to Road");
        type2.add("Illegal Road Cutting");
        type2.add("Repairs of Nala / Drain");
        type2.add("Water logging");
        type2.add("Desilting of Nala / Drain");
        type2.add("Manhole Cover Open");
        type2.add("Drainage Line Damage");
        type2.add("No Water Supply in Public Toilets");
        type2.add("Overflowing of Nala / Drain");
        type2.add("Fencing of Nala /Drain");
        type2.add("Improvements in Burial Grounds");
    }

    private void initItems3() {
        type3.add("Non Glowing of Street Lights");
        type3.add("New Street Light Requirements");
        type3.add("Street Lights Glowing Continuously");
        type3.add("Timer Settings of Street Lights");
        type3.add("Loose Wire Connections");
    }

    private void initItems4(){
        type4.add("Drainage Overflow");
        type4.add("Garbage Burning");
        type4.add("Road side Banners/Posters/Flags/Flexes");
        type4.add("Birth and Death");
        type4.add("Unhygienic food quality in Hotel");
        type4.add("Garbage Nuisance while Transporting");
        type4.add("Garbage vehicle Not Arrived");
        type4.add("Public Toilet Cleaning");
        type4.add("Mosquitoes Fogging required");
        type4.add("Reporting on Malaria");
        type4.add("Stray dogs/Pig/Monkeys/Cattle");
        type4.add("Illegal slaughter of Animals");
        type4.add("Vikarabad");
        type4.add("Building / Wall Collapse");
        type4.add("Fallen tree Branch");
        type4.add("Maintenance in Park");
    }

    private void initItems5(){
        type5.add("");
        type5.add("");
        type5.add("");
        type5.add("");
        type5.add("");
        type5.add("");
        type5.add("");
        type5.add("");
        type5.add("");
    }

    private void initItems6(){

    }

    private void initItems7(){

    }

    private void initItems8(){

    }

    private void initItems9(){

    }

    private void initItems10(){

    }

    private void initItems11(){

    }

    private void initItems12(){

    }

    private void initItems13(){

    }

    private void initItems14(){

    }

    private void initItems15(){

    }

    private void initItems16(){

    }

    private void initItems17(){

    }

    private void initItems18(){

    }

    private void initItems19(){

    }
}
