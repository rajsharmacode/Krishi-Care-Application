package com.example.project.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Models.CommModel;
import com.example.project.Models.CommentModel;
import com.example.project.ProfilePage;
import com.example.project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommentAdapters extends RecyclerView.Adapter<CommentAdapters.myviewholder3>{

    ArrayList<CommentModel> list;
    Context context;

    public CommentAdapters(ArrayList<CommentModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder3 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.commentdemoicon,viewGroup,false);
        return new myviewholder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder3 myviewholder3, int i) {

        CommentModel model1= list.get(i);

        myviewholder3.commentdata.setText(model1.getCommentuser());


        String personid= model1.getPersonid();



        FirebaseDatabase.getInstance().getReference().child("Profileinfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String proname1=dataSnapshot.child(personid).child("proname").getValue(String.class);
                String pronumber1=dataSnapshot.child(personid).child("pronumber").getValue(String.class);
                String proemail1=dataSnapshot.child(personid).child("proemail").getValue(String.class);
                String proimage1=dataSnapshot.child(personid).child("proimage").getValue(String.class);
                String prostate1=dataSnapshot.child(personid).child("prostate").getValue(String.class);


                myviewholder3.personname.setText(proname1);
                if(proimage1!=null)
                {
                    Glide.with(myviewholder3.personimg.getContext()).load(proimage1).into(myviewholder3.personimg);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder3 extends RecyclerView.ViewHolder
    {
        TextView commentdata;
        TextView personname;
        ImageView personimg;

        public myviewholder3(@NonNull View itemView) {
            super(itemView);

            commentdata=itemView.findViewById(R.id.commenttextsend);
            personimg=itemView.findViewById(R.id.personimage1);
            personname=itemView.findViewById(R.id.personname);

        }
    }

}
