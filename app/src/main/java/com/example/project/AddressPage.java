package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;

public class AddressPage extends AppCompatActivity {

    EditText addfullname,addphonenum,addpincode,addcity,addplotno,addaddress;
    ImageButton addbackbtn;
    Spinner addstate;
    Button addsavebtn;
    ArrayAdapter<String> adapter;


    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reference;
    FirebaseUser user;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_page);
        reference=FirebaseDatabase.getInstance().getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();

        addfullname=findViewById(R.id.addfulname);
        addphonenum=findViewById(R.id.addphonenum);
        addpincode=findViewById(R.id.addpincode);
        addstate=findViewById(R.id.addstate);
        addcity=findViewById(R.id.addcity);
        addplotno=findViewById(R.id.addplotno);
        addaddress=findViewById(R.id.addaddress);
        addsavebtn=findViewById(R.id.addsavebtn);
        addbackbtn=findViewById(R.id.addbackbtn);

        spinnerStateList();
        showAddress();
        addsavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addfullname.getText().toString().isEmpty())
                {
                    addfullname.setError("Fill Detial");
                }
                else if(addphonenum.getText().toString().isEmpty())
                {
                    addphonenum.setError("Fill Detial");
                }
                else if(addpincode.getText().toString().isEmpty())
                {
                    addpincode.setError("Fill Detial");
                }
                else if(addcity.getText().toString().isEmpty())
                {
                    addcity.setError("Fill Detial");
                }
                else if(addplotno.getText().toString().isEmpty())
                {
                    addplotno.setError("Fill Detial");
                }
                else if(addaddress.getText().toString().isEmpty())
                {
                    addaddress.setError("Fill Detial");
                }
                else if(addstate.getSelectedItem().toString().isEmpty())
                {
                    Toast.makeText(AddressPage.this, "Enter State", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    String spdata=addstate.getSelectedItem().toString();
                    try {
                        //progressBar.setVisibility(View.VISIBLE);
                        HashMap<String, Object> hashMap = new HashMap<>();
                        Intent i=new Intent(AddressPage.this,InvoiceItemPage.class);
                        hashMap.put("name",addfullname.getText().toString());
                        hashMap.put("phno",addphonenum.getText().toString());
                        hashMap.put("pincode",addpincode.getText().toString());
                        hashMap.put("city",addcity.getText().toString());
                        hashMap.put("state",spdata);
                        hashMap.put("plot",addplotno.getText().toString());
                        hashMap.put("address",addaddress.getText().toString());

                        //set data to db
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AddressOfUser");
                        ref.child(uid)
                                .setValue(hashMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        //progressBar.setVisibility(View.GONE);
                                        //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                                        Toast.makeText(AddressPage.this, "Address inserted", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(AddressPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }catch (Exception e)
                    {
                        Toast.makeText(AddressPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    String num="1";
                    Intent i=new Intent(AddressPage.this,InvoiceItemPage.class);
//                    i.putExtra("name",addfullname.getText().toString());
//                    i.putExtra("phno",addphonenum.getText().toString());
//                    i.putExtra("pincode",addpincode.getText().toString());
//                    i.putExtra("city",addcity.getText().toString());
//                    i.putExtra("state",spdata);
//                    i.putExtra("plot",addplotno.getText().toString());
//                    i.putExtra("address",addaddress.getText().toString());
//                    i.putExtra("clickable",num);
                    startActivity(i);
                    finish();
                }
            }
        });



    }
    private void spinnerStateList() {
        String[] countryCodes = {"Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"};

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryCodes);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addstate.setAdapter(adapter);
        int defaultIndex = adapter.getPosition("Gujarat");
        //addstate.setSelection(defaultIndex);

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
                addfullname.setText(name);
                addphonenum.setText(phno);
                addpincode.setText(pincode);
                addcity.setText(city);
                addplotno.setText(plot);
                addaddress.setText(address);
                int defaultIndex = adapter.getPosition(state);
                addstate.setSelection(defaultIndex);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //progressBar.setVisibility(View.GONE);
                Toast.makeText(AddressPage.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}