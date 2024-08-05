package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Adaptors.AddtoCartAdapter;
import com.example.project.Adaptors.ShopItemAdapters;
import com.example.project.Models.AddtoCartModel;
import com.example.project.Models.ShopItemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddtoCartPage extends AppCompatActivity {

    RecyclerView recview;
    TextView title;
    ImageView back;

    ArrayList<AddtoCartModel> list;
    DatabaseReference reference;
    AddtoCartAdapter adpt;
    ImageView emptycartimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addto_cart_page);


        recview=findViewById(R.id.cartrecview);
        title=findViewById(R.id.carttitle);
        back=findViewById(R.id.cartback);
        emptycartimg=findViewById(R.id.emptycartimg);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        list=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference();
        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adpt=new AddtoCartAdapter(list,getApplicationContext());
        recview.setAdapter(adpt);
        reference.child("AddtoCart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    emptycartimg.setVisibility(View.GONE);
                    list.clear();
                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
                    {
                        AddtoCartModel data=snapshot.getValue(AddtoCartModel.class);
                        list.add(data);
                    }
                }
                else
                {
                    list.clear();
                    Toast.makeText(AddtoCartPage.this, "Cart's Empty", Toast.LENGTH_SHORT).show();
                }
                adpt.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        adpt.notifyDataSetChanged();
    }
}