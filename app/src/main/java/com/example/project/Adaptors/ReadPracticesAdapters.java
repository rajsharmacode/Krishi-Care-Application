package com.example.project.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Models.ReadPracticesModel;
import com.example.project.Models.ShopCategoryModel;
import com.example.project.R;
import com.example.project.ReadPracticesFullPage;

import java.util.ArrayList;

public class ReadPracticesAdapters extends RecyclerView.Adapter<ReadPracticesAdapters.holder8>{

    ArrayList<ReadPracticesModel> list;
    Context context;

    public ReadPracticesAdapters(ArrayList<ReadPracticesModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder8 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.read_practices_demo_icon,viewGroup,false);
        return new holder8(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder8 holder8, int i) {
        ReadPracticesModel model=list.get(i);
        holder8.readname.setText(model.getReadname());
        Glide.with(holder8.readimg.getContext()).load(model.getReadimg()).into(holder8.readimg);

        holder8.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context.getApplicationContext(), ReadPracticesFullPage.class);
                i.putExtra("readdata",model.getReaddata());
                i.putExtra("readimg",model.getReadimg());
                i.putExtra("readname",model.getReadname());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder8 extends RecyclerView.ViewHolder
    {

        TextView readname;
        ImageView readimg;
        public holder8(@NonNull View itemView) {
            super(itemView);

            readname=itemView.findViewById(R.id.readname);
            readimg=itemView.findViewById( R.id.readimg);
        }
    }
}
