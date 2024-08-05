package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project.Adaptors.ShopItemAdapters;
import com.example.project.Models.ShopItemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class SerachItemPage extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recview;

    ShopItemAdapters adpt;
    ShopItemModel model;
    ArrayList<ShopItemModel> datalist;

    ArrayList<ShopItemModel> list;
    DatabaseReference reference;
    ImageView serchback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach_item_page);

        searchView=findViewById(R.id.searchdata);
        recview=findViewById(R.id.searchrecview);
        serchback=findViewById(R.id.serchback);
        searchView.clearFocus();

        serchback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        list=new ArrayList<>();

        reference= FirebaseDatabase.getInstance().getReference();
        recview.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
//        recview.setNestedScrollingEnabled(false);
        adpt=new ShopItemAdapters(list,getApplicationContext());
        recview.setAdapter(adpt);




        reference.child("AllItemProduct").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {

                    ShopItemModel data=snapshot.getValue(ShopItemModel.class);

//                    ;+String.valueOf(data)
//                    Toast.makeText(SerachItemPage.this, data.getItemname(), Toast.LENGTH_SHORT).show();
//                    if(data.getItemname().toLowerCase().contains(s))
//                    {
                        list.add(data);
//                    }

                }
                adpt.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                reference.child("AllItemProduct").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        list.clear();
                        for(DataSnapshot snapshot: dataSnapshot.getChildren())
                        {

                            ShopItemModel data=snapshot.getValue(ShopItemModel.class);

//                    ;+String.valueOf(data)
//                            Toast.makeText(SerachItemPage.this, data.getItemname(), Toast.LENGTH_SHORT).show();
                            if(data.getItemname().toLowerCase().contains(s))
                            {
                                list.add(data);
                            }

                        }
                        adpt.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                });
                return true;
            }
        });




//        reference.child("Shopitemdata").child("Tonics").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//                String proname1=dataSnapshot.child("proname").getValue(String.class);
//                if(dataclass.getItemname().toLowerCase().contains(text.toLowerCase()))
//                {
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });



    }
//    public void searchList(String text){
//        ArrayList<ShopItemModel> searchlist=new ArrayList<>();
//        for(ShopItemModel dataclass : datalist)
//        {
//            if(dataclass.getItemname().toLowerCase().contains(text.toLowerCase()))
//            {
//                searchlist.add(dataclass);
//            }
//        }
//        adpt.searchDataList(searchlist);
//    }
}