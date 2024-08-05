package com.example.project.Adaptors;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.CommentAskcommunity;
import com.example.project.Models.CommModel;
import com.example.project.Models.CommentModel;
import com.example.project.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommunityAdapter extends  RecyclerView.Adapter<CommunityAdapter.myviewholder>{

    ArrayList<CommModel> list;
    Context context;

    public CommunityAdapter(ArrayList<CommModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.communitydemoicon,viewGroup,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder myviewholder, int i) {

        CommModel model=list.get(i);
        myviewholder.question.setText(model.getCommques());
        myviewholder.disc.setText(model.getCommdisc());
//        myviewholder.pp.setText(model.get());
//        Glide.with(myviewholder.personprofileimg.getContext()).load(model.getCommproimg()).into(myviewholder.personprofileimg);
        Glide.with(myviewholder.img.getContext()).load(model.getCommimage()).into(myviewholder.img);

        myviewholder.deletepost.setVisibility(View.GONE);
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String uid=user.getUid();
        String personid= model.getUid();
        if(model.getUid().equals(uid))
        {
            myviewholder.deletepost.setVisibility(View.VISIBLE);
            myviewholder.deletepost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    builder.setTitle("Delete ?")
                            .setMessage("Do You Want to Delete this User")
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String userkey=model.getPostid();
                                    if(userkey!=null)
                                    {
                                        FirebaseDatabase.getInstance().getReference().child("AskCommunity").child(userkey).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(context, "Post Deleted", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                    else
                                    {
                                        Toast.makeText(context, "Wait", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(context, userkey, Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Negative button clicked
                                }
                            })
                            .show();
                }
            });
        }




        FirebaseDatabase.getInstance().getReference().child("Profileinfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String proname1=dataSnapshot.child(personid).child("proname").getValue(String.class);
                String pronumber1=dataSnapshot.child(personid).child("pronumber").getValue(String.class);
                String proemail1=dataSnapshot.child(personid).child("proemail").getValue(String.class);
                String proimage1=dataSnapshot.child(personid).child("proimage").getValue(String.class);
                String prostate1=dataSnapshot.child(personid).child("prostate").getValue(String.class);


                myviewholder.pp.setText(proname1);
                if(proimage1!=null)
                {
                    Glide.with(myviewholder.personprofileimg.getContext()).load(proimage1).into(myviewholder.personprofileimg);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



//        notifyDataSetChanged();
        myviewholder.like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(myviewholder.like.isChecked())
                {
                                myviewholder.commcounter.setText("1");

                }
                else
                {
                    myviewholder.commcounter.setText("0");

                }
            }
        });
//        if(myviewholder.like.isChecked())
//        {
//
//        }
//        else
//        {
//            myviewholder.commcounter.setText("0");
//        }


        myviewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, CommentAskcommunity.class);
                i.putExtra("question",model.getCommques());
                i.putExtra("disc",model.getCommdisc());
                i.putExtra("image",model.getCommimage());
                i.putExtra("postid",model.getPostid());
                i.putExtra("uid",model.getUid());
//                i.putExtra("personname",model.getCommproname());
                context.startActivity(i);
            }
        });

//        myviewholder.comment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(context, CommentAskcommunity.class);
//                i.putExtra("question",model.getCommques());
//                i.putExtra("disc",model.getCommdisc());
//                i.putExtra("image",model.getCommimage());
//                i.putExtra("postid",model.getPostid());
////                i.putExtra("personimg",model.getCommproimg());
////                i.putExtra("personname",model.getCommproname());
//                context.startActivity(i);
//            }
//        });



            FirebaseDatabase.getInstance().getReference().child("CommentSend").child(model.getPostid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    myviewholder.count=0;
//                    list.clear();
                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
                    {

                        CommentModel data=snapshot.getValue(CommentModel.class);
//                        list.add(data);
                        myviewholder.count++;
//                    list.clear();

                    }
//                adpt.notifyAll();
//                    adpt.notifyDataSetChanged();
                        myviewholder.comentcounter.setText(String.valueOf(myviewholder.count));


//                adpt=new Cmodel33333(list,);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }

            });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class myviewholder extends RecyclerView.ViewHolder
    {


        TextView question,disc;
        ImageView img;
        CheckBox like;
        ImageView comment,more;
        ImageView personprofileimg;
        TextView pp,commcounter;
        ImageView deletepost;
        TextView comentcounter;
        int count=0;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            question=itemView.findViewById(R.id.cometquestion);
            disc=itemView.findViewById(R.id.cometdisc);
            img=itemView.findViewById(R.id.comentimage);
            like=itemView.findViewById(R.id.like);
            comment=itemView.findViewById(R.id.comment);
//            more=itemView.findViewById(R.id.ib_more);
            personprofileimg=itemView.findViewById(R.id.personimage1);
            pp=itemView.findViewById(R.id.commproname);
            commcounter=itemView.findViewById(R.id.commcounter);
            deletepost=itemView.findViewById(R.id.deletepost);
            comentcounter=itemView.findViewById(R.id.comentcounter);
        }
    }






/*
public class CommunityAdapter extends FirebaseRecyclerAdapter<CommModel,CommunityAdapter.myviewholder> {

    public CommunityAdapter(@NonNull FirebaseRecyclerOptions<CommModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder myviewholder, int i, @NonNull CommModel commModel) {

        myviewholder.question.setText(commModel.getCommques());
        myviewholder.disc.setText(commModel.getCommdisc());
        Glide.with(myviewholder.img.getContext()).load(commModel.getCommimage()).into(myviewholder.img);

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.communitydemoicon,viewGroup,false);
        return new myviewholder(v);
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {

        TextView question,disc;
        ImageView img;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            question=itemView.findViewById(R.id.commquestion1);
            disc=itemView.findViewById(R.id.commdesc1);
            img=itemView.findViewById(R.id.commimage1);


        }
    }*/
}
