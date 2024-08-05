package com.example.project.FrontMainPages;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.project.Adaptors.ShopItemAdapters;
import com.example.project.CalculatorPage;
import com.example.project.CameraScanDesease;
import com.example.project.CultivationPage;
import com.example.project.LeaveDeseaePage;
import com.example.project.Models.ShopItemModel;
import com.example.project.PestandDesease;
import com.example.project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    CardView card_fertilizer,card_jiwadu,card_cultivation,card_leave;
    ImageView take_a_picture;
    RecyclerView recview;



    ShopItemAdapters adpt1;

    ArrayList<ShopItemModel> list66;
    DatabaseReference reference;

    private AdapterViewFlipper adapterViewFlipper;
    private int[] images = {R.drawable.xyzq, R.drawable.farmer_banner1};
    private String[] imageNames = {"One", "Two", "Three"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        ImageSlider imageSlider=v.findViewById(R.id.imageSlider);


        final List<SlideModel> model=new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("BannerSlider").addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                model.clear();
                for(DataSnapshot data: dataSnapshot.getChildren())
                    model.add(new SlideModel(data.child("url").getValue().toString(),ScaleTypes.FIT));

                imageSlider.setImageList(model,ScaleTypes.FIT);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Toast.makeText(getContext(), model.get(i).getTitle().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doubleClick(int i) {

            }
        });


//        ArrayList<SlideModel> slideModels=new ArrayList<>();
//
//
//
//        slideModels.add(new SlideModel(R.drawable.farmer_banner1, ScaleTypes.FIT));
//        slideModels.add(new SlideModel(R.drawable.criyagen, ScaleTypes.FIT));
//        slideModels.add(new SlideModel(R.drawable.criyage1, ScaleTypes.FIT));
//        slideModels.add(new SlideModel(R.drawable.xyzq, ScaleTypes.FIT));
//
//        imageSlider.setImageList(slideModels,ScaleTypes.FIT);


        card_fertilizer =v.findViewById(R.id.card_fertilizer);
        card_leave =v.findViewById(R.id.cartleavdesease);
//        card_jiwadu = v.findViewById(R.id.cartJiwadudesease);
        card_cultivation = v.findViewById(R.id.cartcultivationtips);
        take_a_picture = v.findViewById(R.id.take_a_picture);
        recview = v.findViewById(R.id.mainrecview);

        card_cultivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(), CultivationPage.class);
                startActivity(i);
            }
        });
        card_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(), LeaveDeseaePage.class);
                startActivity(i);
            }
        });

        card_fertilizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(), CalculatorPage.class);
                startActivity(i);
            }
        });

        take_a_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CameraScanDesease.class));
            }
        });

//        card_pests.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), PestandDesease.class));
//            }
//        });


        list66=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference();
        recview.setLayoutManager(new GridLayoutManager(getContext(),2));
//        recview.setNestedScrollingEnabled(false);
        adpt1=new ShopItemAdapters(list66,getContext());
        recview.setAdapter(adpt1);
        reference.child("AllItemProduct").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list66.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    ShopItemModel data=snapshot.getValue(ShopItemModel.class);
                    list66.add(data);
                }
                adpt1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });



        return v;
    }
}