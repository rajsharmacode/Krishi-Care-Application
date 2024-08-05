package com.example.project.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.FullItemdetailPage;
import com.example.project.Models.AddtoCartModel;
import com.example.project.Models.ShopItemModel;
import com.example.project.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddtoCartAdapter extends RecyclerView.Adapter<AddtoCartAdapter.holder7>{


    ArrayList<AddtoCartModel> list;
    Context context;

    public AddtoCartAdapter(ArrayList<AddtoCartModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder7 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.adtocart_demo_icon,viewGroup,false);
        return new holder7(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder7 holder7, int i) {

        AddtoCartModel model=list.get(i);

//        AlertDialog.Builder builder;
//        builder=new AlertDialog.Builder(context);
        holder7.cartremovebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                builder.setTitle("efw")
//               .setMessage("Delete...?")
//
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
////                        FirebaseDatabase.getInstance().getReference().child("raj")
////                                .child(getRef(position).getKey()).removeValue();
//                    }
//                })
//
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                            }
//                        })
//                        .show();





                String tempdata= model.getItemid();
//
//                if(tempdata==null) {
//                    Toast.makeText(context.getApplicationContext(), "Wait ", Toast.LENGTH_SHORT).show();
//                }else
//                {

//                    String data2=FirebaseDatabase.getInstance().getReference().child("AddtoCart").child().getKey();
//                    Toast.makeText(context.getApplicationContext(),"", Toast.LENGTH_SHORT).show();
                    FirebaseDatabase.getInstance().getReference().child("AddtoCart").child(tempdata).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void unused) {
//                            notifyDataSetChanged();
//                            list.remove(i);
                            // Notify adapter about the item removal
//                            notifyItemRemoved(i);
                            Toast.makeText(context.getApplicationContext(), "Cart Remove", Toast.LENGTH_SHORT).show();

                        }
                    });
//                }
            }
        });

        holder7.cartname.setText(model.getItemname());
        holder7.cartprice.setText("₹ "+String.valueOf(Float.parseFloat(model.getItemprice())));
        holder7.cartsize.setText("Size :"+model.getItemqnt());
        Glide.with(holder7.cartimg.getContext()).load(model.getItemimg()).into(holder7.cartimg);

        holder7.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(context, FullItemdetailPage.class);
                i1.putExtra("itemdisc",model.getItemdisc());
                i1.putExtra("itemimg",model.getItemimg());
                i1.putExtra("itemprice",model.getItemprice());
                i1.putExtra("itemname",model.getItemname());
                i1.putExtra("itemqnt",model.getItemqnt());
                i1.putExtra("itemid",model.getItemqnt());
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class holder7 extends RecyclerView.ViewHolder
    {

        TextView cartname,cartprice,cartsize;
        Button cartremovebtn;
        ImageView cartimg;
        public holder7(@NonNull View itemView) {
            super(itemView);

            cartimg=itemView.findViewById(R.id.cartimg);
            cartname=itemView.findViewById(R.id.cartname);
            cartprice=itemView.findViewById(R.id.cartprice);
            cartsize=itemView.findViewById(R.id.cartsize);
            cartremovebtn=itemView.findViewById(R.id.cartremovebtn);

        }
    }
}
