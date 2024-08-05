package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.prefs.PreferenceChangeEvent;

public class ContactUs extends AppCompatActivity {

    TextView adminaddress,adminemail,adminmobile,adminwhatapp;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        adminemail=findViewById(R.id.adminemail);
        adminaddress=findViewById(R.id.adminaddress);
        adminmobile=findViewById(R.id.adminmobile);
        adminwhatapp=findViewById(R.id.adminwhatapp);

        reference=FirebaseDatabase.getInstance().getReference();
        reference.child("AdminContactUs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mobile=dataSnapshot.child("admincontactusid").child("mobile").getValue(String.class);
                String whatapp=dataSnapshot.child("admincontactusid").child("whatsapp").getValue(String.class);
                String address=dataSnapshot.child("admincontactusid").child("address").getValue(String.class);
                String email=dataSnapshot.child("admincontactusid").child("email").getValue(String.class);


                adminmobile.setText(mobile);
                adminwhatapp.setText(whatapp);
                adminaddress.setText(address);
                adminemail.setText(email);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}