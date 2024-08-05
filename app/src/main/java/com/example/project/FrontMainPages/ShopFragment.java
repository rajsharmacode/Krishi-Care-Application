package com.example.project.FrontMainPages;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Adaptors.CommunityAdapter;
import com.example.project.Adaptors.ShopCategoryAdaptor;
import com.example.project.AskCommunityPage;
import com.example.project.Models.CommModel;
import com.example.project.Models.ShopCategoryModel;
import com.example.project.R;
import com.example.project.ShopItem;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ShopFragment extends Fragment {

    RecyclerView recview;
    DatabaseReference df;
    ShopCategoryAdaptor adpt;
    TextView txtdemo;
    ExtendedFloatingActionButton floatingbtn;
    Button btnn;

    ArrayList<ShopCategoryModel> list;
    DatabaseReference reference;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_shop, container, false);

        recview=(RecyclerView) view.findViewById(R.id.recviewcategory);
//        txtdemo=(TextView) view.findViewById(R.id.catid);


        list=new ArrayList<>();

        reference=FirebaseDatabase.getInstance().getReference();
        recview.setLayoutManager(new LinearLayoutManager(getContext()));
//        recview.setNestedScrollingEnabled(false);
        adpt=new ShopCategoryAdaptor(list,getContext());
        recview.setAdapter(adpt);
        reference.child("ShopCategory").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {

                    ShopCategoryModel data=snapshot.getValue(ShopCategoryModel.class);
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




/*
        recviewcat.setLayoutManager(new GridLayoutManager(getContext(),1));

        df = FirebaseDatabase.getInstance().getReference().child("ShopCategory");

        FirebaseRecyclerOptions<ShopCategoryModel> options =
                new FirebaseRecyclerOptions.Builder<ShopCategoryModel>()
                        .setQuery(df, ShopCategoryModel.class)
                        .build();
        adpt = new ShopCategoryAdaptor(options);
        recviewcat.setAdapter(adpt);
//        adpt.notifyDataSetChanged();


//        floatingbtn=view.findViewById(R.id.ftbtn);
        btnn=view.findViewById(R.id.btnn);
//        floatingbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(getActivity(), AskCommunityPage.class);
//                startActivity(i);
//            }
//        });
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getActivity(), ShopItem.class);
                startActivity(i1);
            }
        });
*/

        return view;


    }
/*
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