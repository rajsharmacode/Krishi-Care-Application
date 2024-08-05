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
import com.example.project.Models.JivModel;
import com.example.project.R;
import com.example.project.ReadPracticesFullPage;

import java.util.ArrayList;

public class JivAdapters extends RecyclerView.Adapter<JivAdapters.holder11>{

    ArrayList<JivModel> list;
    Context context;

    public JivAdapters(ArrayList<JivModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder11 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.read_practices_demo_icon,viewGroup,false);
        return new holder11(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder11 holder11, int i) {
        JivModel model=list.get(i);
        holder11.readname.setText(model.getJivname());
        Glide.with(holder11.readimg.getContext()).load(model.getJivimg()).into(holder11.readimg);

        holder11.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(context.getApplicationContext(), CaltivationFullPage.class);
                i1.putExtra("readdata",model.getJivdisc());
                i1.putExtra("readimg",model.getJivimg());
                i1.putExtra("readname",model.getJivname());
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder11 extends RecyclerView.ViewHolder
    {
        TextView readname;
        ImageView readimg;
        public holder11(@NonNull View itemView) {
            super(itemView);

            readname=itemView.findViewById(R.id.readname);
            readimg=itemView.findViewById( R.id.readimg);
        }
    }
}
