package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project.Adaptors.CommentAdapters;
import com.example.project.Adaptors.CommunityAdapter;
import com.example.project.Models.CommModel;
import com.example.project.Models.CommentModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CommentAskcommunity extends AppCompatActivity {

    String ques,disc,image;

    TextView ques1,disc1;
    ImageView coverimg;
    RecyclerView recview;
    ArrayList<CommentModel> list;
    DatabaseReference reference;
    CommentAdapters adpt;
    EditText data1;
    ImageButton sendbtn;
    FirebaseUser user;

    String uid;



    ImageView personimg11;
    TextView personamae11;
    ImageButton cometback;
    CheckBox cometlike;
    TextView cometcounter,cometcountercomment;
    ImageButton cometcomment,commetmenuitem;
    int position;
    MenuItem menuItem1;
    ImageView cometeditbtn;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_askcommunity);

        ques=getIntent().getStringExtra("question");
        disc=getIntent().getStringExtra("disc");
        image=getIntent().getStringExtra("image");
        String postid=getIntent().getStringExtra("postid");
        String personid=getIntent().getStringExtra("uid");
//        String personimg=getIntent().getStringExtra("personimg");
//        String personname=getIntent().getStringExtra("personname");

        ques1=findViewById(R.id.cometquestion);
        disc1=findViewById(R.id.cometdisc);
        coverimg=findViewById(R.id.comentimage);
        recview=findViewById(R.id.recyclerView4);
        sendbtn=findViewById(R.id.sendbtn);
        data1=findViewById(R.id.sendedittext);
        personamae11=findViewById(R.id.cometpersonname);
        personimg11=findViewById(R.id.cometpersonimage1);
        cometback=findViewById(R.id.cometback);
        cometlike=findViewById(R.id.cometlike);
        cometcounter=findViewById(R.id.cometcounter);
        cometcomment=findViewById(R.id.cometcomment);
        cometcountercomment=findViewById(R.id.cometcountercomment);
        commetmenuitem=findViewById(R.id.commetmenuitem);
        cometeditbtn=findViewById(R.id.cometeditbtn);

        cometeditbtn.setVisibility(View.GONE);
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();


        if(personid.equals(uid))
        {
            cometeditbtn.setVisibility(View.VISIBLE);
            cometeditbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent i=new Intent(getApplicationContext(),UpdateCommPost.class);
                    i.putExtra("question",ques);
                    i.putExtra("disc",disc);
                    i.putExtra("image",image);
                    i.putExtra("postid",postid);
                    i.putExtra("uid",personid);
                    startActivity(i);

                }
            });
        }


        FirebaseDatabase.getInstance().getReference().child("like").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                count=0;
                list.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {

                    CommentModel data=snapshot.getValue(CommentModel.class);
                    list.add(data);
                    count++;
                    cometcounter.setText(String.valueOf(count));
//                    list.clear();

                }
//                adpt.notifyAll();
//                adpt.notifyDataSetChanged();


//                adpt=new Cmodel33333(list,);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });


        cometlike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {



                if(cometlike.isChecked())
                {


//                    cometcounter.setText("1");



                    try {

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("like").child(postid);

                        //get current user uid, since user is registered so we can get now

                            //setup data to add in db
//                    progressBar.setVisibility(View.VISIBLE);
                            HashMap<String, Object> hashMap = new HashMap<>();
//                            hashMap.put("postid", postid);
                            hashMap.put("uidd", uid);


                            //set data to db
                            ref.setValue(hashMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
//                                    progressBar.setVisibility(View.GONE);
                                            //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
//                                    onBackPressed();
//                                            data1.setText("");
//                                            Toast.makeText(CommentAskcommunity.this, "Comment inserted", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(Exception e) {
                                            Toast.makeText(CommentAskcommunity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });




                    }catch (Exception e)
                    {
                        Toast.makeText(CommentAskcommunity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }




                }
                else
                {


                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("like").child(postid);
                    ref.removeValue();

                    cometcounter.setText("0");

                }
            }
        });
        cometback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ques1.setText(ques);
        disc1.setText(disc);
        Glide.with(getApplicationContext()).load(image).into(coverimg);
//        personamae11.setText(personname);

        FirebaseDatabase.getInstance().getReference().child("Profileinfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String proname1=dataSnapshot.child(personid).child("proname").getValue(String.class);
                String pronumber1=dataSnapshot.child(personid).child("pronumber").getValue(String.class);
                String proemail1=dataSnapshot.child(personid).child("proemail").getValue(String.class);
                String proimage1=dataSnapshot.child(personid).child("proimage").getValue(String.class);
                String prostate1=dataSnapshot.child(personid).child("prostate").getValue(String.class);


                personamae11.setText(proname1);
                if(proimage1!=null)
                {
                    Glide.with(getApplicationContext()).load(proimage1).into(personimg11);//


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        Glide.with(getApplicationContext()).load(personimg).into(personimg11);//


//        Toast.makeText(getApplicationContext(), ques+disc+image, Toast.LENGTH_SHORT).show();

        list=new ArrayList<>();

        reference= FirebaseDatabase.getInstance().getReference();

        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recview.setNestedScrollingEnabled(false);
        adpt=new CommentAdapters(list,getApplicationContext());
        recview.setAdapter(adpt);
        if(postid==null)
        {

        }else {
            reference.child("CommentSend").child(postid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                     count=0;
                    list.clear();
                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
                    {

                        CommentModel data=snapshot.getValue(CommentModel.class);
                        list.add(data);
                        count++;
                        cometcountercomment.setText(String.valueOf(count));
//                    list.clear();

                    }
//                adpt.notifyAll();
                    adpt.notifyDataSetChanged();


//                adpt=new Cmodel33333(list,);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }

            });
        }


        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data= data1.getText().toString();

                try {

                    if(data.isEmpty())
                    {

                    }else
                    {
                        long timestamp = System.currentTimeMillis();

                        //get current user uid, since user is registered so we can get now

                        //setup data to add in db
//                    progressBar.setVisibility(View.VISIBLE);
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("commentuser", data);
                        hashMap.put("personid", uid);


                        //set data to db
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("CommentSend").child(postid);
                        ref.push()
                                .setValue(hashMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
//                                    progressBar.setVisibility(View.GONE);
                                        //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
//                                    onBackPressed();
                                        data1.setText("");
                                        Toast.makeText(CommentAskcommunity.this, "Comment inserted", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(CommentAskcommunity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }



                }catch (Exception e)
                {
                    Toast.makeText(CommentAskcommunity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

//        menuItem1=findViewById(R.id.editbtnnz);

//        Toast.makeText(getApplicationContext(), ""+String.valueOf(menuItem), Toast.LENGTH_SHORT).show();

        //findViewById(R.id.editbtnn); // Find the menu item

        commetmenuitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu=new PopupMenu(CommentAskcommunity.this,commetmenuitem);
                popupMenu.getMenuInflater().inflate(R.menu.popup_community,popupMenu.getMenu());
//
////
////                MenuItem i;
//
//
////                {
////                    menuItem1.setVisible(false);
//                    Toast.makeText(CommentAskcommunity.this, "visivblr"+menuItem1.getTitle().toString(), Toast.LENGTH_SHORT).show();
//
////                }
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

//                        int i=menuItem.getItemId();
//                        position=menuItem.getItemId();
//                        MenuItem menuItem1 = findViewById(i);

//                        Toast.makeText(CommentAskcommunity.this, ""+i+1+menuItem.getTitle(), Toast.LENGTH_SHORT).show();

                        switch( menuItem.getTitle().toString() )
                        {
//                            case "Edit":
//
//                                Toast.makeText(CommentAskcommunity.this, "Edit bhaiya", Toast.LENGTH_SHORT).show();
//                                return true;
                            case "Report":


                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ReportCommPost");

                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("commdisc", disc);
                                hashMap.put("commimage", image);
                                hashMap.put("commques", ques);
                                hashMap.put("uid", personid);
                                hashMap.put("postid", postid);
                                if(postid==null)
                                {
                                    Toast.makeText(getApplicationContext(), "post id is null", Toast.LENGTH_SHORT).show();
                                }else {
                                    ref.child(postid)
                                            .setValue(hashMap)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(getApplicationContext(), "Community Posted", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(Exception e) {
                                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }


                                return true;
                            default:
                                Toast.makeText(CommentAskcommunity.this, "default", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                    }
                });
//                popupMenu.setOnMenuItemClickListener(this);
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        Toast.makeText(CommentAskcommunity.this, menuItem.getItemId(), Toast.LENGTH_SHORT).show();
//
//                        int i= menuItem.getItemId();
//                        switch (menuItem.getItemId()){
////                            case 0:
////                                Toast.makeText(CommentAskcommunity.this, "edit", Toast.LENGTH_SHORT).show();
////                                return true;
////                            case 1:
////                                Toast.makeText(CommentAskcommunity.this, "report", Toast.LENGTH_SHORT).show();
////                                return true;
//                            default:
////                                Toast.makeText(CommentAskcommunity.this, menuItem.getItemId(), Toast.LENGTH_SHORT).show();
//                                return true;
//                        }
//                    }
//                });
                popupMenu.show();
            }
        });




    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.popup_community,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id= item.getItemId();
//        if(id==R.id.editbtnn){
//            Toast.makeText(getApplicationContext(), "edit", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}