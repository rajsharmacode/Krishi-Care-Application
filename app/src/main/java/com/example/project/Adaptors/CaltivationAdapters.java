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
import com.example.project.CaltivationFullPage;
import com.example.project.Models.CaltivationModel;
import com.example.project.Models.ReadPracticesModel;
import com.example.project.R;
import com.example.project.ReadPracticesFullPage;

import java.util.ArrayList;

public class CaltivationAdapters extends RecyclerView.Adapter<CaltivationAdapters.holder9>{


    ArrayList<CaltivationModel> list;
    Context context;

    public CaltivationAdapters(ArrayList<CaltivationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder9 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.read_practices_demo_icon,viewGroup,false);
        return new holder9(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder9 holder9, int i) {
        CaltivationModel model=list.get(i);
        holder9.readname.setText(model.getCaltname());
        Glide.with(holder9.readimg.getContext()).load(model.getCaltimg()).into(holder9.readimg);

        holder9.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(context.getApplicationContext(), CaltivationFullPage.class);
                i1.putExtra("readdata",model.getCaltdisc());
                i1.putExtra("readimg",model.getCaltimg());
                i1.putExtra("readname",model.getCaltname());
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder9 extends RecyclerView.ViewHolder
    {
        TextView readname;
        ImageView readimg;

        public holder9(@NonNull View itemView) {
            super(itemView);

            readname=itemView.findViewById(R.id.readname);
            readimg=itemView.findViewById( R.id.readimg);
        }
    }
}
