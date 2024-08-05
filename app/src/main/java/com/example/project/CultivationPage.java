package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project.Adaptors.CaltivationAdapters;
import com.example.project.Adaptors.ReadPracticesAdapters;
import com.example.project.Models.CaltivationModel;
import com.example.project.Models.ReadPracticesModel;
import com.example.project.Models.ShopItemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CultivationPage extends AppCompatActivity {

    RecyclerView recview;
    CaltivationAdapters adpt1;

    ArrayList<CaltivationModel> list66;
    DatabaseReference reference;
    SearchView searchView;
    ImageView caltback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivation_page);


        recview=findViewById(R.id.caltrecview);
        searchView=findViewById(R.id.searchdata);
        caltback=findViewById(R.id.caltback);
        searchView.clearFocus();


        list66=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference();
        recview.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
//        recview.setNestedScrollingEnabled(false);
        adpt1=new CaltivationAdapters(list66,getApplicationContext());
        recview.setAdapter(adpt1);
        reference.child("CaltivationTips").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list66.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    CaltivationModel data=snapshot.getValue(CaltivationModel.class);
                    list66.add(data);
                }
                adpt1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });



        caltback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                reference.child("CaltivationTips").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        list66.clear();
                        for(DataSnapshot snapshot: dataSnapshot.getChildren())
                        {

                            CaltivationModel data=snapshot.getValue(CaltivationModel.class);

//                    ;+String.valueOf(data)
//                            Toast.makeText(SerachItemPage.this, data.getItemname(), Toast.LENGTH_SHORT).show();
                            if(data.getCaltname().toLowerCase().contains(s))
                            {
                                list66.add(data);
                            }

                        }
                        adpt1.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                });
                return true;
            }
        });

    }
}