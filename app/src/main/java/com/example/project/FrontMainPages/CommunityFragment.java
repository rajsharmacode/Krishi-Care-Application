package com.example.project.FrontMainPages;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project.Adaptors.CommunityAdapter;
import com.example.project.AskCommunityFragment;
import com.example.project.AskCommunityPage;
import com.example.project.Models.CommModel;
import com.example.project.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CommunityFragment extends Fragment {

    RecyclerView recview;
    CommunityAdapter adpt;
    ExtendedFloatingActionButton floatingbtn;
    ArrayList<CommModel> list;
    DatabaseReference reference;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_community, container, false);


        recview=(RecyclerView) view.findViewById(R.id.recview);
        floatingbtn=view.findViewById(R.id.floatingbtn);
        list=new ArrayList<>();

        reference=FirebaseDatabase.getInstance().getReference();

//        reference.child("AskCommunity").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot snapshot: dataSnapshot.getChildren())
//                {
//
//                    CommModel data=snapshot.getValue(CommModel.class);
//                    list.add(data);
////                    list.clear();
//
//                }
//
//                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
//                recview.setLayoutManager(linearLayoutManager);
//                recview.setNestedScrollingEnabled(false);
//                CommunityAdapter adpt=new CommunityAdapter(list,getContext());
//                recview.setAdapter(adpt);
////                adpt=new Cmodel33333(list,);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
//        recview.setLayoutManager(linearLayoutManager);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));
        recview.setNestedScrollingEnabled(false);
        adpt=new CommunityAdapter(list,getContext());
        recview.setAdapter(adpt);
        reference.child("AskCommunity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {

                    CommModel data=snapshot.getValue(CommModel.class);
                    list.add(data);
//                    list.clear();

                }
//                adpt.notifyAll();
                adpt.notifyDataSetChanged();


//                adpt=new Cmodel33333(list,);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });




        floatingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AskCommunityPage.class));
            }
        });




/*
        recview=(RecyclerView) view.findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        df = FirebaseDatabase.getInstance().getReference().child("AskCommunity");
        
        FirebaseRecyclerOptions<CommModel> options =
                new FirebaseRecyclerOptions.Builder<CommModel>()
                        .setQuery(df, CommModel.class)
                        .build();
        adpt = new CommunityAdapter(options);
        recview.setAdapter(adpt);
//        adpt.notifyDataSetChanged();


        floatingbtn=view.findViewById(R.id.floatingbtn);
        floatingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), AskCommunityPage.class);
                startActivity(i);
//                replaceFragment(new AskCommunityFragment());
            }
        });*/
        return view;
    }
   /* private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        adpt.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adpt.stopListening();

    }*/
}