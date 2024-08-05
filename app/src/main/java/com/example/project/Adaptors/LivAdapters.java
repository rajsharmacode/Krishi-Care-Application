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
import com.example.project.Models.LivModel;
import com.example.project.R;
import com.example.project.ReadPracticesFullPage;

import java.util.ArrayList;

public class LivAdapters extends  RecyclerView.Adapter<LivAdapters.holder10>{

    ArrayList<LivModel> list;
    Context context;

    public LivAdapters(ArrayList<LivModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder10 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.read_practices_demo_icon,viewGroup,false);
        return new holder10(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder10 holder10, int i) {
        LivModel model=list.get(i);
        holder10.readname.setText(model.getLivname());
        Glide.with(holder10.readimg.getContext()).load(model.getLivimg()).into(holder10.readimg);

        holder10.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(context.getApplicationContext(), CaltivationFullPage.class);
                i1.putExtra("readdata",model.getLivdisc());
                i1.putExtra("readimg",model.getLivimg());
                i1.putExtra("readname",model.getLivname());
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder10 extends RecyclerView.ViewHolder
    {
        TextView readname;
        ImageView readimg;

        public holder10(@NonNull View itemView) {
            super(itemView);

            readname=itemView.findViewById(R.id.readname);
            readimg=itemView.findViewById( R.id.readimg);
        }
    }
}
