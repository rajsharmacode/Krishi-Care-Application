package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Adaptors.AddtoCartAdapter;
import com.example.project.Adaptors.MyorderAdapters;
import com.example.project.Models.AddtoCartModel;
import com.example.project.Models.MyOrderModel;
import com.example.project.Models.ShopItemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyorderPage extends AppCompatActivity {






    RecyclerView recview;
    TextView title;
    ImageView back;

    ArrayList<MyOrderModel> list;
    DatabaseReference reference;
    MyorderAdapters adpt;
    ImageView emptycartimg;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder_page);


        recview=findViewById(R.id.myorderrecview);
        backbtn=findViewById(R.id.backbtn);
//        title=findViewById(R.id.carttitle);
//        back=findViewById(R.id.cartback);
//        emptycartimg=findViewById(R.id.emptycartimg);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        list=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference();
        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adpt=new MyorderAdapters(list,getApplicationContext());
        recview.setAdapter(adpt);
        reference.child("MyOrder").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
//                    emptycartimg.setVisibility(View.GONE);
                    list.clear();
                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
                    {
                        MyOrderModel data=snapshot.getValue(MyOrderModel.class);
                        list.add(data);
                    }
                }
                else
                {
                    list.clear();
                    Toast.makeText(MyorderPage.this, "Cart's Empty", Toast.LENGTH_SHORT).show();
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