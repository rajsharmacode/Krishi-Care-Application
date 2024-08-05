package com.example.project.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.AddressPage;
import com.example.project.FullItemdetailPage;
import com.example.project.InvoiceItemPage;
import com.example.project.Models.ShopCategoryModel;
import com.example.project.Models.ShopItemModel;
import com.example.project.R;
import com.example.project.ShopItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopItemAdapters extends RecyclerView.Adapter<ShopItemAdapters.holder6>{

    ArrayList<ShopItemModel> list;
    Context context;

//    ArrayList<ShopItemModel> dataList;

    public ShopItemAdapters(ArrayList<ShopItemModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder6 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_shop_demoicon,viewGroup,false);
        return new holder6(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder6 holder6, int i) {
        ShopItemModel model=list.get(i);
        holder6.itemname.setText(model.getItemname());
        holder6.itemprice.setText("₹ "+String.valueOf(Float.parseFloat(model.getItemprice())));
        Glide.with(holder6.itemimage.getContext()).load(model.getItemimg()).into(holder6.itemimage);

        holder6.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(context, FullItemdetailPage.class);
                i1.putExtra("itemdisc",model.getItemdisc());
                i1.putExtra("itemimg",model.getItemimg());
                i1.putExtra("itemprice",model.getItemprice());
                i1.putExtra("itemname",model.getItemname());
                i1.putExtra("itemqnt",model.getItemqnt());
                i1.putExtra("itemid",model.getItemid());
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i1);
            }
        });
        holder6.adtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String temp= model.getItemid();
                    Intent i=new Intent(context.getApplicationContext(),AddtoCartAdapter.class);
                    i.putExtra("raajid",model.getItemid());
                    //progressBar.setVisibility(View.VISIBLE);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    //Intent i=new Intent(AddressPage.this, InvoiceItemPage.class);
                    hashMap.put("itemdisc",model.getItemdisc());
                    hashMap.put("itemimg",model.getItemimg());
                    hashMap.put("itemprice",model.getItemprice());
                    hashMap.put("itemname",model.getItemname());
                    hashMap.put("itemqnt",model.getItemqnt());
                    hashMap.put("itemid",model.getItemid());

                    //set data to db
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AddtoCart");
                    ref.child(temp)
                            .setValue(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    //progressBar.setVisibility(View.GONE);
                                    //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                                    Toast.makeText(context.getApplicationContext(), "Your's Cart is Added", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {
                                    Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }catch (Exception e)
                {
                    Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(context, "Data Added To Cart", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    public void searchDataList(ArrayList<ShopItemModel> searchList){
//        list = searchList;
//        notifyDataSetChanged();
//    }

    public class holder6 extends RecyclerView.ViewHolder
    {
        ImageView itemimage;
        Button adtocart;
        TextView itemname,itemprice;

        public holder6(@NonNull View itemView) {
            super(itemView);

            itemname=itemView.findViewById(R.id.itemname1);
            itemprice=itemView.findViewById(R.id.itemprice1);
            itemimage=itemView.findViewById(R.id.itemimg1);
            adtocart=itemView.findViewById(R.id.adtocart1);


        }
    }

}
