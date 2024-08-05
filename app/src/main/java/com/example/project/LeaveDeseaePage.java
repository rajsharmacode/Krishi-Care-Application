package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project.Adaptors.LivAdapters;
import com.example.project.Models.LivModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LeaveDeseaePage extends AppCompatActivity {

    RecyclerView recview;
    LivAdapters adpt1;

    ArrayList<LivModel> list66;
    DatabaseReference reference;
    SearchView searchView;
    ImageView caltback;
    Spinner pestspinner;

    ArrayAdapter<String> adapter;
    ArrayList<String> ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_deseae_page);

        recview = findViewById(R.id.caltrecview);
        searchView = findViewById(R.id.searchdata);
        caltback = findViewById(R.id.caltback);
        pestspinner = findViewById(R.id.pestspinner);
        searchView.clearFocus();




        caltback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        spinnerStateList();

        ll=new ArrayList<>();
      //  String[] countryCodes = {"Cabbage", "Cucumber", "Brinjal", "Okra", "Onion","Pigeon Pea","Peanut","Potato","Rice","Tomato","Soybean","Wheat"};

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ll);

        reference=FirebaseDatabase.getInstance().getReference("LeaveDesease");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot item:dataSnapshot.getChildren())
                {
                    ll.add(item.getKey());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pestspinner.setAdapter(adapter);
//        int defaultIndex = adapter.getPosition("Cabbage");
//        pestspinner.setSelection(defaultIndex);
        pestspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String selectedCropCategory = countryCodes[i];
                String selectedCropCategory =pestspinner.getSelectedItem().toString();

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        reference.child("LeaveDesease").child(selectedCropCategory).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                list66.clear();
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                                    LivModel data = snapshot.getValue(LivModel.class);

//                    ;+String.valueOf(data)
//                            Toast.makeText(SerachItemPage.this, data.getItemname(), Toast.LENGTH_SHORT).show();
                                    if (data.getLivname().toLowerCase().contains(s)) {
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




//                if (selectedCropCategory.equals("Cabbage")) {
//
//                }


                list66 = new ArrayList<>();
                reference = FirebaseDatabase.getInstance().getReference();
                recview.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
//        recview.setNestedScrollingEnabled(false);
                adpt1 = new LivAdapters(list66, getApplicationContext());
                recview.setAdapter(adpt1);
                reference.child("LeaveDesease").child(selectedCropCategory).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list66.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            LivModel data = snapshot.getValue(LivModel.class);
                            list66.add(data);
                        }
                        adpt1.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                });



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void spinnerStateList() {


    }
}