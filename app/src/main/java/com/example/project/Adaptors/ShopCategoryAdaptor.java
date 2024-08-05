package com.example.project.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.CommentAskcommunity;
import com.example.project.Models.CommModel;
import com.example.project.Models.ShopCategoryModel;
import com.example.project.R;
import com.example.project.ShopItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class ShopCategoryAdaptor extends RecyclerView.Adapter<ShopCategoryAdaptor.holder5> {


    ArrayList<ShopCategoryModel> list;
    Context context;

    public ShopCategoryAdaptor(ArrayList<ShopCategoryModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder5 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.shopcategorydemo,viewGroup,false);
        return new holder5(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder5 holder5, int i) {
        ShopCategoryModel model=list.get(i);
        holder5.catname.setText(model.getCatname());
        Glide.with(holder5.catimg.getContext()).load(model.getCatimage()).into(holder5.catimg);
        holder5.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempda=model.getCatname();
                String userkey1=model.getUserkey();
                Intent i=new Intent(context, ShopItem.class);
                i.putExtra("catname22",tempda);
                i.putExtra("userkey",userkey1);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder5 extends RecyclerView.ViewHolder
    {
        ImageView catimg;
        TextView catname;
        CardView cardView1;
        public holder5(@NonNull View itemView) {
            super(itemView);

            catimg=itemView.findViewById(R.id.catimage);
            catname=itemView.findViewById(R.id.catname);
            cardView1=itemView.findViewById(R.id.gridview1);

        }
    }
}


















/*extends FirebaseRecyclerAdapter<ShopCategoryModel,ShopCategoryAdaptor.myviewholder> {

    public ShopCategoryAdaptor(@NonNull FirebaseRecyclerOptions<ShopCategoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder myviewholder, int i, @NonNull ShopCategoryModel shopCategoryModel) {
        myviewholder.catname.setText(shopCategoryModel.getCatname());
        Glide.with(myviewholder.catimg.getContext()).load(shopCategoryModel.getCatimage()).into(myviewholder.catimg);

        myviewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(view.getContext(), ShopItem.class);
                view.getContext().startActivity(i1);
            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shopcategorydemo,viewGroup,false);
        return new ShopCategoryAdaptor.myviewholder(v);
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {

        ImageView catimg;
        TextView catname;
        CardView cardView1;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            catimg=itemView.findViewById(R.id.catimage);
            catname=itemView.findViewById(R.id.catname);
            cardView1=itemView.findViewById(R.id.gridview1);


        }
    }*/

