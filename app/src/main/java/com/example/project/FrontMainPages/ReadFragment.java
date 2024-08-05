package com.example.project.FrontMainPages;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project.Adaptors.ReadPracticesAdapters;
import com.example.project.Adaptors.ShopItemAdapters;
import com.example.project.Models.ReadPracticesModel;
import com.example.project.Models.ShopItemModel;
import com.example.project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ReadFragment extends Fragment {


    RecyclerView recview;



    ReadPracticesAdapters adpt1;

    ArrayList<ReadPracticesModel> list66;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_read, container, false);


        recview=view.findViewById(R.id.readrecview);

        list66=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference();
        recview.setLayoutManager(new GridLayoutManager(getContext(),2));
//        recview.setNestedScrollingEnabled(false);
        adpt1=new ReadPracticesAdapters(list66,getContext());
        recview.setAdapter(adpt1);
        reference.child("ReadPractices").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list66.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    ReadPracticesModel data=snapshot.getValue(ReadPracticesModel.class);
                    list66.add(data);
                }
                adpt1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });



        return view;
    }
}