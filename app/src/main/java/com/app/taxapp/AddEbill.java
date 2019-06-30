package com.app.taxapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class AddEbill extends AppCompatActivity {

    TextInputEditText cn,address,billno,uscno,billscno,charge,total;
    Button dist,billero,billdate,due,store;
    DatabaseReference data;
    Electricbilldetails info;
    SpinnerDialog distspinnerDialog;
    SpinnerDialog erospinnerDialog;

    //southern power organisation details
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<String> list3 = new ArrayList<>();
    ArrayList<String> list4 = new ArrayList<>();
    ArrayList<String> list5 = new ArrayList<>();
    ArrayList<String> list6 = new ArrayList<>();
    ArrayList<String> list7 = new ArrayList<>();
    ArrayList<String> list8 = new ArrayList<>();
    ArrayList<String> list9 = new ArrayList<>();
    ArrayList<String> list10 = new ArrayList<>();
    ArrayList<String> list11 = new ArrayList<>();
    ArrayList<String> list12 = new ArrayList<>();
    ArrayList<String> list13 = new ArrayList<>();
    ArrayList<String> list14 = new ArrayList<>();

    //N&D organisation details
    ArrayList<String> north = new ArrayList<>();
    ArrayList<String> nd1 = new ArrayList<>();
    ArrayList<String> nd2 = new ArrayList<>();
    ArrayList<String> nd3 = new ArrayList<>();
    ArrayList<String> nd4 = new ArrayList<>();
    ArrayList<String> nd5 = new ArrayList<>();
    ArrayList<String> nd6 = new ArrayList<>();
    ArrayList<String> nd7 = new ArrayList<>();
    ArrayList<String> nd8 = new ArrayList<>();
    ArrayList<String> nd9 = new ArrayList<>();
    ArrayList<String> nd10 = new ArrayList<>();
    ArrayList<String> nd11 = new ArrayList<>();
    ArrayList<String> nd12 = new ArrayList<>();
    ArrayList<String> nd13 = new ArrayList<>();
    ArrayList<String> nd14 = new ArrayList<>();
    ArrayList<String> nd15 = new ArrayList<>();
    ArrayList<String> nd16 = new ArrayList<>();
    ArrayList<String> nd17 = new ArrayList<>();

    Spinner org;
    int year;
    int month;
    int dayofMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ebill);

        org = (Spinner)findViewById(R.id.org);
        dist = (Button)findViewById(R.id.dist);
        cn = findViewById(R.id.cn);
        address =findViewById(R.id.address);
        billno =findViewById(R.id.billno);
        billdate =(Button)findViewById(R.id.billdate);
        uscno =findViewById(R.id.uscno);
        billscno =findViewById(R.id.billscno);
        billero =(Button)findViewById(R.id.billero);
        due =(Button)findViewById(R.id.due);
        charge =findViewById(R.id.charge);
        total =findViewById(R.id.total);
        store = (Button)findViewById(R.id.store);

        info = new Electricbilldetails();
        data = FirebaseDatabase.getInstance().getReference().child("Electricbilldetails");

        final List<String> power = new ArrayList<String>();
        power.add("TSSPDCL(Southern Power)");
        power.add("TSNPDCL(Northern Power)");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,power);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        org.setAdapter(arrayAdapter);
        org.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                org.setSelection(i);
                if (i == 0){
                    list.clear();
                    initItems1();
                    distspinnerDialog = new SpinnerDialog(AddEbill.this,list,"Select District");
                    distspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                        @Override
                        public void onClick(final String list, int position) {
                            dist.setText(list);

                            billero.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(list.isEmpty()){
                                        Toast.makeText(AddEbill.this,"Please select District",Toast.LENGTH_LONG).show();
                                    }else {
                                        erospinnerDialog.showSpinerDialog();
                                    }
                                }
                            });
                            switch (list) {
                                case "Hyderabad":list1.clear();
                                    initItems2();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, list1, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String list1, int position) {
                                            billero.setText(list1);
                                        }
                                    });break;
                                case "Jogulamba":list2.clear();
                                    initItems3();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, list2, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String list2, int position) {
                                            billero.setText(list2);
                                        }
                                    });break;
                                case "Mahbubnagar":list3.clear();
                                    initItems4();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, list3, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String list3, int position) {
                                            billero.setText(list3);
                                        }
                                    });break;
                                case "Medak":list4.clear();
                                    initItems5();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, list4, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String list4, int position) {
                                            billero.setText(list4);
                                        }
                                    });break;
                                case "Medchal":list5.clear();
                                    initItems6();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, list5, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String list5, int position) {
                                            billero.setText(list5);
                                        }
                                    });break;
                                case "Nagarkurnool":list6.clear();
                                    initItems7();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, list6, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String list6, int position) {
                                            billero.setText(list6);
                                        }
                                    });break;
                                case "Nalgonda":list7.clear();
                                    initItems8();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, list7, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String list7, int position) {
                                            billero.setText(list7);
                                        }
                                    });break;
                                case "Ranga Reddy":list8.clear();
                                    initItems9();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, list8, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String list8, int position) {
                                            billero.setText(list8);
                                        }
                                    });break;
                                case "Sangareddy":list9.clear();
                                    initItems10();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, list9, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String list9, int position) {
                                            billero.setText(list9);
                                        }
                                    });break;
                                case "Siddipet":list10.clear();
                                    initItems11();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, list10, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String list10, int position) {
                                            billero.setText(list10);
                                        }
                                    });break;
                                case "Suryapet":list11.clear();
                                    initItems12();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, list11, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String list11, int position) {
                                            billero.setText(list11);
                                        }
                                    });break;
                                case "Vikarabad":list12.clear();
                                    initItems13();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, list12, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String list12, int position) {
                                            billero.setText(list12);
                                        }
                                    });break;
                                case "Wanaparthy":
                                    list13.clear();
                                    initIems14();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, list13, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String list13, int position) {
                                            billero.setText(list13);
                                        }
                                    });break;
                                case "Yadadri":
                                    list14.clear();
                                    initItems15();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, list14, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String list14, int position) {
                                            billero.setText(list14);
                                        }
                                    });break;

                            }
                        }
                    });

                }else if (i == 1){
                    north.clear();
                    Items1();
                    distspinnerDialog = new SpinnerDialog(AddEbill.this,north,"Select District");
                    distspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                        @Override
                        public void onClick(final String north, int position) {
                            dist.setText(north);

                            billero.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(north.isEmpty()){
                                        Toast.makeText(AddEbill.this,"Please select District",Toast.LENGTH_LONG).show();
                                    }else {
                                        erospinnerDialog.showSpinerDialog();
                                    }
                                }
                            });
                            switch (north) {
                                case "ADILABAD":nd1.clear();
                                    Items2();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd1, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd1, int position) {
                                            billero.setText(nd1);
                                        }
                                    });break;
                                case "BHADRADRI":nd2.clear();
                                    Items3();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd2, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd2, int position) {
                                            billero.setText(nd2);
                                        }
                                    });break;
                                case "JAGTIAL":nd3.clear();
                                    Items4();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd3, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd3, int position) {
                                            billero.setText(nd3);
                                        }
                                    });break;
                                case "JANGAON":nd4.clear();
                                    Items5();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd4, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd4, int position) {
                                            billero.setText(nd4);
                                        }
                                    });break;
                                case "JAYASHANKAR":nd5.clear();
                                    Items6();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd5, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd5, int position) {
                                            billero.setText(nd5);
                                        }
                                    });break;
                                case "KAMAREDDY":nd6.clear();
                                    Items7();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd6, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd6, int position) {
                                            billero.setText(nd6);
                                        }
                                    });break;
                                case "KARIMNAGAR":nd7.clear();
                                    Items8();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd7, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd7, int position) {
                                            billero.setText(nd7);
                                        }
                                    });break;
                                case "KHAMMAM":nd8.clear();
                                    Items9();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd8, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd8, int position) {
                                            billero.setText(nd8);
                                        }
                                    });break;
                                case "KOMARAMBHEEM":nd9.clear();
                                    Items10();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd9, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd9, int position) {
                                            billero.setText(nd9);
                                        }
                                    });break;
                                case "MAHABUBABAD":nd10.clear();
                                    Items11();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd10, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd10, int position) {
                                            billero.setText(nd10);
                                        }
                                    });break;
                                case "MANCHERIAL":nd11.clear();
                                    Items12();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd11, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd11, int position) {
                                            billero.setText(nd11);
                                        }
                                    });break;
                                case "NIRMAL":nd12.clear();
                                    Items13();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd12, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd12, int position) {
                                            billero.setText(nd12);
                                        }
                                    });break;
                                case "NIZAMABAD":
                                    nd13.clear();
                                    Items14();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd13, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd13, int position) {
                                            billero.setText(nd13);
                                        }
                                    });break;
                                case "PEDDAPALLI":
                                    nd14.clear();
                                    Items15();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd14, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd14, int position) {
                                            billero.setText(nd14);
                                        }
                                    });break;
                                case "RAJANNA":
                                    nd15.clear();
                                    Items16();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd15, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd15, int position) {
                                            billero.setText(nd15);
                                        }
                                    });break;
                                case "WARANGAL RURAL":
                                    nd16.clear();
                                    Items17();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd16, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd16, int position) {
                                            billero.setText(nd16);
                                        }
                                    });break;
                                case "WARANGAL URBAN":
                                    nd17.clear();
                                    Items18();
                                    erospinnerDialog = new SpinnerDialog(AddEbill.this, nd17, "Select ERO Name");
                                    erospinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                        @Override
                                        public void onClick(String nd17, int position) {
                                            billero.setText(nd17);
                                        }
                                    });break;

                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distspinnerDialog.showSpinerDialog();
            }
        });

        billdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calender = Calendar.getInstance();
                year = calender.get(Calendar.YEAR);
                month = calender.get(Calendar.MONTH);
                dayofMonth = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEbill.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                billdate.setText(day + " / " + (month + 1) + " / " + year);
                            }
                        }, year, month, dayofMonth);
                datePickerDialog.show();
            }
        });

        due.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calender = Calendar.getInstance();
                year = calender.get(Calendar.YEAR);
                month = calender.get(Calendar.MONTH);
                dayofMonth = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEbill.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                due.setText(day + " / " + (month + 1) + " / " + year);
                            }
                        }, year, month, dayofMonth);
                datePickerDialog.show();
            }
        });

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dt = dist.getText().toString().trim();
                String cusno = cn.getText().toString().trim();
                String addr = address.getText().toString().trim();
                String bero = billero.getText().toString().trim();
                String dd = due.getText().toString().trim();
                String bd = billdate.getText().toString().trim();
                String bn =billno.getText().toString().trim();
                String us =uscno.getText().toString().trim();
                String sc =billscno.getText().toString().trim();
                String charg =charge.getText().toString().trim();
                String totalamt =total.getText().toString().trim();

                if (!dt.equals("") && !bero.equals("") && !cusno.equals("") && !addr.equals("") && !bn.equals("") && !bd.equals("") && !us.equals("") && !sc.equals("") && !dd.equals("") && !charg.equals("") && !totalamt.equals("") ){

                    info.setDist(dt);
                    info.setCn(cusno);
                    info.setAddress(addr);
                    info.setEro(bero);
                    info.setDue(dd);
                    info.setBilldate(bd);
                    info.setBillno(bn);
                    info.setUscno(us);
                    info.setBillscno(sc);
                    info.setCharge(charg);
                    info.setTotal(totalamt);

                    data.child(sc).setValue(info);

                    Toast.makeText(AddEbill.this,"BillData Submitted !!",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(AddEbill.this,"Please fill all the fields ",Toast.LENGTH_LONG).show();
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

            Intent intent = new Intent(AddEbill.this,Adminhome.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //SOUTHERN POWER FUNCTIONS
    public void initItems1(){
        list.add("Hyderabad");
        list.add("Jogulamba");
        list.add("Mahbubnagar");
        list.add("Medak");
        list.add("Medchal");
        list.add("Nagarkurnool");
        list.add("Nalgonda");
        list.add("Ranga Reddy");
        list.add("Sangareddy");
        list.add("Siddipet");
        list.add("Suryapet");
        list.add("Vikarabad");
        list.add("Wanaparthy");
        list.add("Yadadri");

    }

    public void initItems2(){
        list1.add("A C GUARDS");
        list1.add("AZAMABAD");
        list1.add("BANJARA HILLS");
        list1.add("BOWENPALLY");
        list1.add("CHAMPAPET");
        list1.add("CHANCHALGUDA");
        list1.add("GACHIBOWLI");
        list1.add("GAGANPAHAD");
        list1.add("GREENLANDS");
        list1.add("HABSIGUDA");
        list1.add("HAYATNAGAR");
        list1.add("IBRAHIMBAGH");
        list1.add("IBRAHIMPATNAM");
        list1.add("INDIRA PARK");
        list1.add("JEEDIMETLA");
        list1.add("KANDUKUR");
        list1.add("KEESARA");
        list1.add("KONDAPUR");
        list1.add("KUKATPALLY");
        list1.add("MALKAJGIRI");
        list1.add("MEDCHAL");
        list1.add("MINT COMPOUND");
        list1.add("PARADISE");
        list1.add("QUTBULLAHPUR");
        list1.add("RETHIBOWLI");
        list1.add("RP NILAYAM");
        list1.add("SAINIKPURI");
        list1.add("SALAR JUNG");
        list1.add("SANATH NAGAR");
        list1.add("SAROORNAGAR");
        list1.add("SHADNAGAR");
        list1.add("SITHAPHALMANDI");
        list1.add("SULTAN BAZAR");
    }

    public void initItems3(){
        list2.add("GADWAL");
    }
    public void initItems4(){
        list3.add("DEVERKADRA");
        list3.add("JADCHERLA");
        list3.add("MAHBOOB NAGAR");
        list3.add("NARAYANPET");
    }

    public void initItems5(){
        list4.add("MEDAK(EBS)");
        list4.add("TOOPRAN");
    }

    public void initItems6(){
        list5.add("A C GUARDS");
        list5.add("AZAMABAD");
        list5.add("BANJARA HILLS");
        list5.add("BOWENPALLY");
        list5.add("CHAMPAPET");
        list5.add("CHANCHALGUDA");
        list5.add("GACHIBOWLI");
        list5.add("GAGANPAHAD");
        list5.add("GREENLANDS");
        list5.add("HABSIGUDA");
        list5.add("HAYATNAGAR");
        list5.add("IBRAHIMBAGH");
        list5.add("IBRAHIMPATNAM");
        list5.add("INDIRA PARK");
        list5.add("JEEDIMETLA");
        list5.add("KANDUKUR");
        list5.add("KEESARA");
        list5.add("KONDAPUR");
        list5.add("KUKATPALLY");
        list5.add("MALKAJGIRI");
        list5.add("MEDCHAL");
        list5.add("MINT COMPOUND");
        list5.add("PARADISE");
        list5.add("QUTBULLAHPUR");
        list5.add("RETHIBOWLI");
        list5.add("RP NILAYAM");
        list5.add("SAINIKPURI");
        list5.add("SALAR JUNG");
        list5.add("SANATH NAGAR");
        list5.add("SAROORNAGAR");
        list5.add("SHADNAGAR");
        list5.add("SITHAPHALMANDI");
        list5.add("SULTAN BAZAR");
    }

    public void initItems7(){
        list6.add("KALWAKURTHY");
        list6.add("NAGAR KURNOOL");
    }

    public void initItems8(){
        list7.add("DEVARAKONDA");
        list7.add("HALIA");
        list7.add("MIRYALGUDA");
        list7.add("MUNUGODU");
        list7.add("NAKREKAL");
        list7.add("NALGONDA");
        list7.add("NAMPALLY");
    }

    public void initItems9(){
        list8.add("A C GUARDS");
        list8.add("AZAMABAD");
        list8.add("BANJARA HILLS");
        list8.add("BOWENPALLY");
        list8.add("CHAMPAPET");
        list8.add("CHANCHALGUDA");
        list8.add("GACHIBOWLI");
        list8.add("GAGANPAHAD");
        list8.add("GREENLANDS");
        list8.add("HABSIGUDA");
        list8.add("HAYATNAGAR");
        list8.add("IBRAHIMBAGH");
        list8.add("IBRAHIMPATNAM");
        list8.add("INDIRA PARK");
        list8.add("JEEDIMETLA");
        list8.add("KANDUKUR");
        list8.add("KEESARA");
        list8.add("KONDAPUR");
        list8.add("KUKATPALLY");
        list8.add("MALKAJGIRI");
        list8.add("MEDCHAL");
        list8.add("MINT COMPOUND");
        list8.add("PARADISE");
        list8.add("QUTBULLAHPUR");
        list8.add("RETHIBOWLI");
        list8.add("RP NILAYAM");
        list8.add("SAINIKPURI");
        list8.add("SALAR JUNG");
        list8.add("SANATH NAGAR");
        list8.add("SAROORNAGAR");
        list8.add("SHADNAGAR");
        list8.add("SITHAPHALMANDI");
        list8.add("SULTAN BAZAR");
    }

    public void initItems10(){
        list9.add("JOGIPET(EBS)");
        list9.add("PATANCHERU");
        list9.add("SADASIVPET");
        list9.add("SANGAREDDY");
        list9.add("ZAHEERABAD");
    }

    public void initItems11(){
        list10.add("CHERIYAL");
        list10.add("GAJWEL");
        list10.add("HUSNABAD");
        list10.add("SIDDIPET");
    }

    public void initItems12(){
        list11.add("HUZURNAGAR");
        list11.add("KODADA");
        list11.add("SURYAPET");
        list11.add("TUNGATURTHY");
    }

    public void initItems13(){
        list12.add("TANDOOR");
        list12.add("VIKARABAD");
    }

    public void initIems14(){
        list13.add("DEVERKADRA");
        list13.add("WANAPARTY");
    }

    public void initItems15(){
        list14.add("ALER");
        list14.add("BHONGIR");
        list14.add("CHOUTUPPAL");
        list14.add("MOTHUKUR");
        list14.add("RAMANNAPET");
    }

    //NORTH POWER FUNCTIONS
    public void Items1() {
        north.add("ADILABAD");
        north.add("BHADRADRI");
        north.add("JAGTIAL");
        north.add("JANGAON");
        north.add("JAYASHANKAR");
        north.add("KAMAREDDY");
        north.add("KARIMNAGAR");
        north.add("KHAMMAM");
        north.add("KOMARAMBHEEM");
        north.add("MAHABUBABAD");
        north.add("MANCHERIAL");
        north.add("NIRMAL");
        north.add("NIZAMABAD");
        north.add("PEDDAPALLI");
        north.add("RAJANNA");
        north.add("WARANGAL RURAL");
        north.add("WARANGAL URBAN");
    }

    public void Items2() {
        nd1.add("BAINSA");
        nd1.add("KAGAZNAGAR");
        nd1.add("MANCHIRIAL");
        nd1.add("NIRMAL");
        nd1.add("RURAL ADILABAD");
        nd1.add("T ADILABAD");
        nd1.add("UTNOOR");
    }

    public void Items3() {
        nd2.add("ASWARAOPETA");
        nd2.add("BADRACHALAM");
        nd2.add("KOTHAGUDEM");
        nd2.add("MADIRA");
        nd2.add("MANUGURU");
        nd2.add("PALVONCHA");
        nd2.add("R-KHAMMAM");
        nd2.add("SATHUPALLY");
        nd2.add("T KHAMMAM");
        nd2.add("TALLADA");
        nd2.add("YELLANDU");
    }

    public void Items4() {
        nd3.add("GODAVARIKANI");
        nd3.add("HUZURABAD");
        nd3.add("JAGITHYAL");
        nd3.add("JAMMIKUNTA");
        nd3.add("KORUTLA");
        nd3.add("MALLIAL");
        nd3.add("MANTHINI");
        nd3.add("METPALLY");
        nd3.add("PEDDAPALLY");
        nd3.add("R KARIMNAGAR");
        nd3.add("RURAL JAGITYAL");
        nd3.add("T KARIMNAGAR");
    }

    public void Items5() {
        nd4.add("BACHANNAPET");
        nd4.add("BHUPALAPALLY");
        nd4.add("GHANPUR");
        nd4.add("JANAGAON");
        nd4.add("MAHABUBAD");
        nd4.add("MULUGU");
        nd4.add("NARSAMPET");
        nd4.add("PALAKURTHY");
        nd4.add("PARKAL");
        nd4.add("R-HANMAKONDA");
        nd4.add("T HANAMKONDA");
        nd4.add("T WARANGAL");
        nd4.add("THORRUR");
        nd4.add("WARDHANNAPET");
    }

    public void Items6() {
        nd5.add("BACHANNAPET");
        nd5.add("BHUPALAPALLY");
        nd5.add("GHANPUR");
        nd5.add("JANAGAON");
        nd5.add("MAHABUBAD");
        nd5.add("MULUGU");
        nd5.add("NARSAMPET");
        nd5.add("PALAKURTHY");
        nd5.add("PARKAL");
        nd5.add("R-HANMAKONDA");
        nd5.add("T HANAMKONDA");
        nd5.add("T WARANGAL");
        nd5.add("THORRUR");
        nd5.add("WARDHANNAPET");
    }

    public void Items7() {
        nd6.add("ARMOOR");
        nd6.add("BANSWADA");
        nd6.add("BHEEMGEL");
        nd6.add("BHICHKUNDA");
        nd6.add("BODHAN");
        nd6.add("DOMAKONDA");
        nd6.add("KAMAREDDY");
        nd6.add("MORTHAD");
        nd6.add("NADIPET");
        nd6.add("R NIZAMABAD");
        nd6.add("RURAL KAMAREDDY");
        nd6.add("T NIZAMABAD");
        nd6.add("YELLAREDDY");
    }

    public void Items8() {
        nd7.add("ALUGUNUR");
        nd7.add("GODAVARIKANI");
        nd7.add("GUNDI");
        nd7.add("HUZURABAD");
        nd7.add("JAGITHYAL");
        nd7.add("JAMMIKUNTA");
        nd7.add("KORUTLA");
        nd7.add("MANTHINI");
        nd7.add("METPALLY");
        nd7.add("PEDDAPALLY");
        nd7.add("R KARIMNAGAR");
        nd7.add("T KARIMNAGAR");
        nd7.add("TOWN-II KARIMNAGAR");
    }

    public void Items9() {
        nd8.add("BADRACHALAM");
        nd8.add("KOTHAGUDEM");
        nd8.add("KOTHALINGALA");
        nd8.add("KUSUMANCHI");
        nd8.add("MADIRA");
        nd8.add("R-KHAMMAM");
        nd8.add("SATHUPALLY");
        nd8.add("T KHAMMAM");
        nd8.add("TALLADA");
        nd8.add("TOWN-II KHAMMAM");
        nd8.add("WYRA");
        nd8.add("YELLANDU");
    }

    public void Items10() {
        nd9.add("ASIFABAD");
        nd9.add("BAINSA");
        nd9.add("KAGAZNAGAR");
        nd9.add("MANCHIRIAL");
        nd9.add("NIRMAL");
        nd9.add("T ADILABAD");
    }

    public void Items11() {
        nd10.add("A J MILLS WARANGAL");
        nd10.add("BHUPALAPALLY");
        nd10.add("GHANPUR");
        nd10.add("JANAGAON");
        nd10.add("KAZIPET");
        nd10.add("KORIVI");
        nd10.add("MAHABUBAD");
        nd10.add("MULUGU");
        nd10.add("NARSAMPET");
        nd10.add("PARKAL");
        nd10.add("R-HANMAKONDA");
        nd10.add("T HANAMKONDA");
        nd10.add("T WARANGAL");
        nd10.add("THORRUR");
        nd10.add("WARDHANNAPET");
    }

    public void Items12() {
        nd11.add("BAINSA");
        nd11.add("BELLAMPALLY");
        nd11.add("CHENNUR");
        nd11.add("KAGAZNAGAR");
        nd11.add("LUXETTIPET");
        nd11.add("MANCHIRIAL");
        nd11.add("NIRMAL");
        nd11.add("T ADILABAD");
    }

    public void Items13() {
        nd12.add("BAINSA");
        nd12.add("KAGAZNAGAR");
        nd12.add("KHANAPUR");
        nd12.add("MANCHIRIAL");
        nd12.add("NIRMAL");
        nd12.add("T ADILABAD");
    }

    public void Items14() {
        nd13.add("ARMOOR");
        nd13.add("BANSWADA");
        nd13.add("BHEEMGEL");
        nd13.add("BODHAN");
        nd13.add("DOMAKONDA");
        nd13.add("KAMAREDDY");
        nd13.add("MORTHAD");
        nd13.add("NADIPET");
        nd13.add("NAVIPET");
        nd13.add("PERKIT");
        nd13.add("R NIZAMABAD");
        nd13.add("T NIZAMABAD");
        nd13.add("TOWN-II NIZAMABAD");
        nd13.add("YELLAREDDY");
    }

    public void Items15() {
        nd14.add("DHARMARAM");
        nd14.add("GODAVARIKANI");
        nd14.add("HUZURABAD");
        nd14.add("JAGITHYAL");
        nd14.add("JAMMIKUNTA");
        nd14.add("KORUTLA");
        nd14.add("MANTHINI");
        nd14.add("METPALLY");
        nd14.add("PEDDAPALLY");
        nd14.add("R KARIMNAGAR");
        nd14.add("RAMAGUNDAM");
        nd14.add("SULTHANABAD");
        nd14.add("T KARIMNAGAR");
    }

    public void Items16() {
        nd15.add("GODAVARIKANI");
        nd15.add("HUZURABAD");
        nd15.add("JAGITHYAL");
        nd15.add("JAMMIKUNTA");
        nd15.add("KORUTLA");
        nd15.add("MANTHINI");
        nd15.add("METPALLY");
        nd15.add("PEDDAPALLY");
        nd15.add("R KARIMNAGAR");
        nd15.add("T KARIMNAGAR");
    }

    public void Items17() {
        nd16.add("BHUPALAPALLY");
        nd16.add("GHANPUR");
        nd16.add("JANAGAON");
        nd16.add("MAHABUBAD");
        nd16.add("MULUGU");
        nd16.add("NARSAMPET");
        nd16.add("PARKAL");
        nd16.add("R-HANMAKONDA");
        nd16.add("T HANAMKONDA");
        nd16.add("T WARANGAL");
        nd16.add("THORRUR");
        nd16.add("WARDHANNAPET");
    }

    public void Items18() {
        nd17.add("A J MILLS WARANGAL");
        nd17.add("BHEEMADEVARAPALLY");
        nd17.add("BHUPALAPALLY");
        nd17.add("GHANPUR");
        nd17.add("JANAGAON");
        nd17.add("KAZIPET");
        nd17.add("MAHABUBAD");
        nd17.add("MULUGU");
        nd17.add("NARSAMPET");
        nd17.add("PARKAL");
        nd17.add("R-HANMAKONDA");
        nd17.add("T HANAMKONDA");
        nd17.add("T WARANGAL");
        nd17.add("THORRUR");
        nd17.add("WARDHANNAPET");
    }
}
