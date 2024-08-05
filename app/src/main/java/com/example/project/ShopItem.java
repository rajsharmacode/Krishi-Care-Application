package com.example.project;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project.Adaptors.ShopCategoryAdaptor;
import com.example.project.Adaptors.ShopItemAdapters;
import com.example.project.Models.ShopCategoryModel;
import com.example.project.Models.ShopItemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShopItem extends AppCompatActivity {

    RecyclerView recview;
    ProgressBar progressBar33;
    TextView categorytopicname;
    ImageView back;

    ArrayList<ShopItemModel> list;
    DatabaseReference reference;
    ShopItemAdapters adpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_item);

        recview=findViewById(R.id.recitem);
        categorytopicname=findViewById(R.id.toolbarname);
        back=findViewById(R.id.btn_backitem);
        progressBar33=findViewById(R.id.progressBar33);

        String data=getIntent().getStringExtra("catname22");
        String userkey=getIntent().getStringExtra("userkey");


        categorytopicname.setText(data);
        back.setOnClickListener(new View.OnClickListener() {
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


        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
        reference.child("Shopitemdata").child(userkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {

                    ShopItemModel data=snapshot.getValue(ShopItemModel.class);
                    list.add(data);
                    progressBar33.setVisibility(View.GONE);
//                    list.clear();

                }
//                adpt.notifyAll();
                adpt.notifyDataSetChanged();


//                adpt=new Cmodel33333(list,);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });





    }
}