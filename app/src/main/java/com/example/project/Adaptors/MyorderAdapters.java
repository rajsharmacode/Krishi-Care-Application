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
import com.example.project.FullItemdetailPage;
import com.example.project.Models.MyOrderModel;
import com.example.project.Models.ShopItemModel;
import com.example.project.MyorderFullPage;
import com.example.project.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MyorderAdapters extends RecyclerView.Adapter<MyorderAdapters.holder14>{

    ArrayList<MyOrderModel> list;
    Context context;

    public MyorderAdapters(ArrayList<MyOrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder14 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.myorder_demo_icon,viewGroup,false);
        return new holder14(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder14 holder14, int i) {
        MyOrderModel model=list.get(i);
        holder14.myorderid.setText("Order ID: "+model.getItemid());
        holder14.myorderprice.setText("₹ "+model.getItemprice());
//        Date currentDate = new Date();
//
//        // Format the date and time
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//        String formattedDate = dateFormat.format(currentDate);
//
//        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
//        String formattedTime = timeFormat.format(currentDate);

        holder14.myordertime.setText("Order Date: "+model.getOrdertime());
        holder14.myorderqnt.setText("Total Quantity: "+model.getItemqnt());
        holder14.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(context, MyorderFullPage.class);
                i1.putExtra("itemdisc",model.getItemdisc());
                i1.putExtra("itemimg",model.getItemimg());
                i1.putExtra("itemprice",model.getItemprice());
                i1.putExtra("itemname",model.getItemname());
                i1.putExtra("itemqnt",model.getItemqnt());
                i1.putExtra("itemid",model.getItemid());
                i1.putExtra("orderid",model.getOrderid());
                i1.putExtra("ordertime",model.getOrdertime());
                i1.putExtra("orderstat",model.getOrderstats());
                i1.putExtra("userid",model.getUserid());
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i1);
            }
        });
//        holder6.itemname.setText(model.getItemname());
//        holder6.itemprice.setText("₹ "+String.valueOf(Float.parseFloat(model.getItemprice())));
//        Glide.with(holder6.itemimage.getContext()).load(model.getItemimg()).into(holder6.itemimage);
//
//        holder6.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i1=new Intent(context, FullItemdetailPage.class);
//                i1.putExtra("itemdisc",model.getItemdisc());
//                i1.putExtra("itemimg",model.getItemimg());
//                i1.putExtra("itemprice",model.getItemprice());
//                i1.putExtra("itemname",model.getItemname());
//                i1.putExtra("itemqnt",model.getItemqnt());
//                i1.putExtra("itemid",model.getItemid());
//                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i1);
//            }
//        });
//        holder6.adtocart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//
//                    String temp= model.getItemid();
//                    Intent i=new Intent(context.getApplicationContext(),AddtoCartAdapter.class);
//                    i.putExtra("raajid",model.getItemid());
//                    //progressBar.setVisibility(View.VISIBLE);
//                    HashMap<String, Object> hashMap = new HashMap<>();
//                    //Intent i=new Intent(AddressPage.this, InvoiceItemPage.class);
//                    hashMap.put("itemdisc",model.getItemdisc());
//                    hashMap.put("itemimg",model.getItemimg());
//                    hashMap.put("itemprice",model.getItemprice());
//                    hashMap.put("itemname",model.getItemname());
//                    hashMap.put("itemqnt",model.getItemqnt());
//                    hashMap.put("itemid",model.getItemid());
//
//                    //set data to db
//                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AddtoCart");
//                    ref.child(temp)
//                            .setValue(hashMap)
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void unused) {
//                                    //progressBar.setVisibility(View.GONE);
//                                    //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
//                                    Toast.makeText(context.getApplicationContext(), "Your's Cart is Added", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(Exception e) {
//                                    Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                }catch (Exception e)
//                {
//                    Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
////                Toast.makeText(context, "Data Added To Cart", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder14 extends RecyclerView.ViewHolder
    {

        TextView myorderid,myorderprice,myorderqnt,myordertime;
        public holder14(@NonNull View itemView) {
            super(itemView);


            myorderid=itemView.findViewById(R.id.myorderid);
            myorderprice=itemView.findViewById(R.id.myorderprice);
            myorderqnt=itemView.findViewById(R.id.myorderqnt);
            myordertime=itemView.findViewById(R.id.myordertime);

        }
    }
}
