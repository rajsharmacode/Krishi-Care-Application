package com.example.project;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class UpdateCommPost extends AppCompatActivity {

    String ques,disc1,image;



    ImageView backbtn;
    EditText question,disc;
    Button postbtn;
    ImageButton imgbtn;

    ActivityResultLauncher<String> launcher;
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reference;
    FirebaseUser user;
    ProgressBar progressBar2;
    Uri temp;
    String uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_comm_post);


        question=findViewById(R.id.questionask);
        disc=findViewById(R.id.discask);
        imgbtn=findViewById(R.id.selectimg);
        postbtn=findViewById(R.id.postbtn);
        progressBar2=findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.GONE);
        backbtn=findViewById(R.id.commbackbtn);

        ques=getIntent().getStringExtra("question");
        disc1=getIntent().getStringExtra("disc");
        image=getIntent().getStringExtra("image");
        String postid=getIntent().getStringExtra("postid");
        String personid=getIntent().getStringExtra("uid");


        question.setText(ques);
        disc.setText(disc1);
        Glide.with(getApplicationContext()).load(image).into(imgbtn);















        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();

        database= FirebaseDatabase.getInstance();
        storage= FirebaseStorage.getInstance();
        launcher=registerForActivityResult(new ActivityResultContracts.GetContent()
                , new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {

                        if (result != null) {
                            imgbtn.setImageURI(result);
                            progressBar2.setVisibility(View.VISIBLE);

                            final StorageReference reference = storage.getReference()
                                    .child("AskCommunity");

                            reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            temp = uri;
                                            progressBar2.setVisibility(View.GONE);

//                                        database.getReference().child("Profileinfo").child(uid).child("proimage")
//                                                .setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                    @Override
//                                                    public void onSuccess(Void unused) {
//                                                        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
//                                                    }
//                                                });
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch("image/*");
            }
        });

        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    long timestamp = System.currentTimeMillis();

                    //get current user uid, since user is registered so we can get now

//                    user= FirebaseAuth.getInstance().getCurrentUser();
//                    uid=user.getUid();
//                    FirebaseDatabase.getInstance().getReference().child("Profileinfo").addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            proname1=dataSnapshot.child(uid).child("proname").getValue(String.class);
//                            String pronumber1=dataSnapshot.child(uid).child("pronumber").getValue(String.class);
//                            String proemail1=dataSnapshot.child(uid).child("proemail").getValue(String.class);
//                            proimage1=dataSnapshot.child(uid).child("proimage").getValue(String.class);
//                            String prostate1=dataSnapshot.child(uid).child("prostate").getValue(String.class);
//
//
////                            myviewholder.personname.setText(proname1);
////                            Glide.with(myviewholder.personprofileimg.getContext()).load(model.getCommimage()).into(myviewholder.personprofileimg);
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
//                        if(proname1==null || proimage1==null)
//                        {
//                            Toast.makeText(AskCommunityPage.this, "any null", Toast.LENGTH_SHORT).show();
//                        }else {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AskCommunity");

//                    String postid=ref.push().getKey();
                    //setup data to add in db
                    if (temp.toString().isEmpty())
                    {

                    }else {
                        progressBar2.setVisibility(View.VISIBLE);
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("commdisc", disc.getText().toString());
                        hashMap.put("commimage", temp.toString());
                        hashMap.put("commques", question.getText().toString());
//                            hashMap.put("commproname", proname1);
//                            hashMap.put("commproimg", proimage1);
//                    hashMap.put("uid", uid);
//                    hashMap.put("postid", postid);
                        Toast.makeText(getApplicationContext(), "proname1", Toast.LENGTH_SHORT).show();
                        if(postid==null)
                        {
                            Toast.makeText(getApplicationContext(), "postid null", Toast.LENGTH_SHORT).show();
                        }else {
                            //set data to db
                            ref.child(postid)
                                    .updateChildren(hashMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            progressBar2.setVisibility(View.GONE);
                                            //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                                            Toast.makeText(getApplicationContext(), "Community Posted", Toast.LENGTH_SHORT).show();
                                            onBackPressed();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(Exception e) {
                                            progressBar2.setVisibility(View.GONE);
                                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }

//                        }
                }catch (Exception e)
                {

                    progressBar2.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "ok"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}