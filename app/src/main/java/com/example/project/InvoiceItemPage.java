package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.project.Adaptors.AddtoCartAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class InvoiceItemPage extends AppCompatActivity implements PaymentResultListener {

    String itemname,itemdisc,itemimg,itemqnt,itemprice,itemid;
    TextView totalcharge;
    TextView payname,payprice,payqnt;
    ImageView payimg;
    TextView payaddress;
    Button payeditbtn,payaddarressbtn;


    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reference;
    FirebaseUser user;
    String uid;

    TextView paytotalmrp,paydiscount,paytsellprice,payshipingcharge,paygranttot;

    RadioButton radiobtn;
    RadioGroup radiogroup;
    Button placeorderbtn;
    int temp;
//    float grantamout;
    String grantamout;

    float realgranttotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_item_page);

        Checkout.preload(getApplicationContext());

        itemdisc=getIntent().getStringExtra("itemdisc");
        itemimg=getIntent().getStringExtra("itemimg");
        itemprice=getIntent().getStringExtra("itemprice");
        itemname=getIntent().getStringExtra("itemname");
        itemqnt=getIntent().getStringExtra("itemqnt");
        itemid=getIntent().getStringExtra("itemid");


//        itemqnt=getIntent().getStringExtra("itemqnt");
//        itemqnt=getIntent().getStringExtra("itemqnt");
//        itemqnt=getIntent().getStringExtra("itemqnt");
        totalcharge=findViewById(R.id.totalcharge);

        payimg=findViewById(R.id.payimg);
        payname=findViewById(R.id.payname);
        payprice=findViewById(R.id.payprice);
        payqnt=findViewById(R.id.payqnt);

        payaddress=findViewById(R.id.payaddress);
        payeditbtn=findViewById(R.id.payeditbtn);
        payaddarressbtn=findViewById(R.id.payaddaddressbtn);

        paytotalmrp=findViewById(R.id.paytotalmrp);
        paydiscount=findViewById(R.id.paydiscount);
        paytsellprice=findViewById(R.id.paytsellprice);
        payshipingcharge=findViewById(R.id.payshipingcharge);
        paygranttot=findViewById(R.id.paygranttot);

        placeorderbtn=findViewById(R.id.placeorderbtn);
        radiogroup=findViewById(R.id.radiogroup);


        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();

        Glide.with(getApplicationContext()).load(itemimg).into(payimg);
        payname.setText(itemname);
        payprice.setText("₹ "+itemprice);
        payqnt.setText("Size "+itemqnt+" kg");
        temp=0;

        showAddress();
        //        String name=getIntent().getStringExtra("name");
//        String phno=getIntent().getStringExtra("phno");
//        String pincode=getIntent().getStringExtra("pincode");
//        String city=getIntent().getStringExtra("city");
//        String state=getIntent().getStringExtra("state");
//        String plot=getIntent().getStringExtra("plot");
//        String address=getIntent().getStringExtra("address");
//        String clickable=getIntent().getStringExtra("clickable");
//        if(clickable==null)
//        {
//
//        }else
//        {
//            int num1=Integer.parseInt(clickable);
//            if (num1 == 1)
//            {
//                payaddress.setText(name+"\n"+phno+"\n"+plot+" "+address+"\n"+city+" "+state+" "+pincode);
//            }
//        }
        //  Toast.makeText(getApplicationContext(), ""+clickable, Toast.LENGTH_SHORT).show();

//        itemprice="500";
        CalculateTotalMRP(itemprice);


        payeditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(InvoiceItemPage.this,AddressPage.class);
                startActivity(i);
            }
        });
        payaddarressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(InvoiceItemPage.this,AddressPage.class);
                startActivity(i);

            }
        });
        placeorderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id=radiogroup.getCheckedRadioButtonId();

                radiobtn=findViewById(id);

               if(temp==1)
                {
                    Toast.makeText(InvoiceItemPage.this, "Enter Address", Toast.LENGTH_SHORT).show();
                }else  if(radiobtn.getText().equals("Cash on delevery"))
                {
                    Intent i1=new Intent(InvoiceItemPage.this,CaseonDelevery.class);
                    i1.putExtra("itemid",itemid);
                    startActivity(i1);


                    try {



//                        user= FirebaseAuth.getInstance().getCurrentUser();
//                        uid=user.getUid();

                        Date currentDate = new Date();

                        // Format the date and time
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String formattedDate = dateFormat.format(currentDate);

                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                        String formattedTime = timeFormat.format(currentDate);
//                        String temp= model.getItemid();
//                        Intent i=new Intent(context.getApplicationContext(), AddtoCartAdapter.class);
//                        i.putExtra("raajid",model.getItemid());
//                        //progressBar.setVisibility(View.VISIBLE);



                        HashMap<String, Object> hashMap = new HashMap<>();
                        //Intent i=new Intent(AddressPage.this, InvoiceItemPage.class);



                        hashMap.put("itemdisc",itemdisc);
                        hashMap.put("itemimg",itemimg);
                        hashMap.put("itemprice",itemprice);
                        hashMap.put("itemname",itemname);
                        hashMap.put("itemqnt",itemqnt);
                        hashMap.put("itemid",itemid);
                        hashMap.put("orderid",itemid);
                        hashMap.put("ordertime", formattedDate +" "+ formattedTime);
                        hashMap.put("orderstats","Processed");
                        hashMap.put("userid",uid);

                        //set data to db
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("MyOrder");
                        ref.child(itemid)
                                .setValue(hashMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        //progressBar.setVisibility(View.GONE);
                                        //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                                        Toast.makeText(getApplicationContext(), "Your's Order is Added", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    try {
                        Date currentDate = new Date();

                        // Format the date and time
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String formattedDate = dateFormat.format(currentDate);

                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                        String formattedTime = timeFormat.format(currentDate);
//                        String temp= model.getItemid();
//                        Intent i=new Intent(context.getApplicationContext(), AddtoCartAdapter.class);
//                        i.putExtra("raajid",model.getItemid());
//                        //progressBar.setVisibility(View.VISIBLE);
                        HashMap<String, Object> hashMap = new HashMap<>();
                        //Intent i=new Intent(AddressPage.this, InvoiceItemPage.class);



                        hashMap.put("itemdisc",itemdisc);
                        hashMap.put("itemimg",itemimg);
                        hashMap.put("itemprice",itemprice);
                        hashMap.put("itemname",itemname);
                        hashMap.put("itemqnt",itemqnt);
                        hashMap.put("itemid",itemid);
                        hashMap.put("orderid",itemid);
                        hashMap.put("ordertime", formattedDate +" "+ formattedTime);
                        hashMap.put("orderstats","Processed");
                        hashMap.put("userid",uid);

                        //set data to db
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("OrderStatus");
                        ref.child(itemid)
                                .setValue(hashMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        //progressBar.setVisibility(View.GONE);
                                        //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                                        Toast.makeText(getApplicationContext(), "sucessss", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }




                } else if (radiobtn.getText().equals("Online Payment")) {
                   makepayment();
               }

            }
        });


    }

    private void CalculateTotalMRP(String amount) {

        float amt=Float.valueOf(amount);
        paytotalmrp.setText("₹ "+amt);
        float disc=(float) (amt*(0.11));
        paydiscount.setText("₹ "+String.valueOf(disc));
        float realprice=(float)(amt-29);
        paytsellprice.setText("₹ "+String.valueOf(realprice));
        float shipcrg=(float) 58;
        payshipingcharge.setText("₹ "+String.valueOf(shipcrg));
        double gt=(float)(amt-disc+shipcrg);
//        grantamout=gt;
        grantamout=String.format("%.2f",gt);
        paygranttot.setText("₹ "+String.valueOf(grantamout));

        realgranttotal=Float.parseFloat(grantamout);

    }


    private void showAddress() {
        reference=FirebaseDatabase.getInstance().getReference();
        reference.child("AddressOfUser").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String name=dataSnapshot.child(uid).child("name").getValue(String.class);
                    String phno=dataSnapshot.child(uid).child("phno").getValue(String.class);
                    String pincode=dataSnapshot.child(uid).child("pincode").getValue(String.class);
                    String city=dataSnapshot.child(uid).child("city").getValue(String.class);
                    String state=dataSnapshot.child(uid).child("state").getValue(String.class);
                    String plot=dataSnapshot.child(uid).child("plot").getValue(String.class);
                    String address=dataSnapshot.child(uid).child("address").getValue(String.class);
                    if(name==null)
                    {
                        temp=1;
                    }else
                    {
                        payaddress.setText(name+"\n"+phno+"\n"+plot+" "+address+"\n"+city+" "+state+" "+pincode);

                    }

            }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //progressBar.setVisibility(View.GONE);
                        Toast.makeText(InvoiceItemPage.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
        });
    }

    private void makepayment(){

//        grantamout=4000;
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_HLcOr9RdD7qKHB");

//        checkout.setImage(R.drawable.logo);
        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Krishi Care");
            options.put("description", "Make safe Payment");
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
//            options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", (realgranttotal*100));//300 X 100
//            options.put("prefill.email", "rajking@gmail.com");
//            options.put("prefill.contact","6352517277");

            JSONObject retryOBJ=new JSONObject();
            retryOBJ.put("enabled",true);
            retryOBJ.put("max_count",4);

            options.put("retry",retryOBJ);
            checkout.open(activity, options);
        } catch(Exception e) {
//            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {

//        Toast.makeText(getApplicationContext(), "Payment Success"+s, Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getApplicationContext(),CaseonDelevery.class);
        i.putExtra("itemid",itemid);
        startActivity(i);
        try {
            Date currentDate = new Date();

            // Format the date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String formattedDate = dateFormat.format(currentDate);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            String formattedTime = timeFormat.format(currentDate);
//                        String temp= model.getItemid();
//                        Intent i=new Intent(context.getApplicationContext(), AddtoCartAdapter.class);
//                        i.putExtra("raajid",model.getItemid());
//                        //progressBar.setVisibility(View.VISIBLE);
            HashMap<String, Object> hashMap = new HashMap<>();
            //Intent i=new Intent(AddressPage.this, InvoiceItemPage.class);



            hashMap.put("itemdisc",itemdisc);
            hashMap.put("itemimg",itemimg);
            hashMap.put("itemprice",itemprice);
            hashMap.put("itemname",itemname);
            hashMap.put("itemqnt",itemqnt);
            hashMap.put("itemid",itemid);
            hashMap.put("orderid",itemid);
            hashMap.put("ordertime", formattedDate +" "+ formattedTime);
            hashMap.put("orderstats","Processed");
            hashMap.put("userid",uid);

            //set data to db
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("MyOrder");
            ref.child(itemid)
                    .setValue(hashMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            //progressBar.setVisibility(View.GONE);
                            //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                            Toast.makeText(getApplicationContext(), "Your's Order is Added", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }







        try {
            Date currentDate = new Date();

            // Format the date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String formattedDate = dateFormat.format(currentDate);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            String formattedTime = timeFormat.format(currentDate);
//                        String temp= model.getItemid();
//                        Intent i=new Intent(context.getApplicationContext(), AddtoCartAdapter.class);
//                        i.putExtra("raajid",model.getItemid());
//                        //progressBar.setVisibility(View.VISIBLE);
            HashMap<String, Object> hashMap = new HashMap<>();
            //Intent i=new Intent(AddressPage.this, InvoiceItemPage.class);



            hashMap.put("itemdisc",itemdisc);
            hashMap.put("itemimg",itemimg);
            hashMap.put("itemprice",itemprice);
            hashMap.put("itemname",itemname);
            hashMap.put("itemqnt",itemqnt);
            hashMap.put("itemid",itemid);
            hashMap.put("orderid",itemid);
            hashMap.put("ordertime", formattedDate +" "+ formattedTime);
            hashMap.put("orderstats","Processed");
            hashMap.put("userid",uid);

            //set data to db
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("OrderStatus");
            ref.child(itemid)
                    .setValue(hashMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            //progressBar.setVisibility(View.GONE);
                            //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                            Toast.makeText(getApplicationContext(), "sucessss", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }





    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(InvoiceItemPage.this, "Payment Failed"+s, Toast.LENGTH_SHORT).show();
    }
}