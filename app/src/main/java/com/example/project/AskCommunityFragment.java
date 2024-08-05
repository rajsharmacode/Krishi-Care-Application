package com.example.project;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class AskCommunityFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_ask_community, container, false);

       /* backbtn=view.findViewById(R.id.backbtn);
        question=view.findViewById(R.id.questionask);
        disc=view.findViewById(R.id.discask);
        imgbtn=view.findViewById(R.id.selectimg);
        postbtn=view.findViewById(R.id.postbtn);
        progressBar2=view.findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.GONE);

        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();

        database= FirebaseDatabase.getInstance();
        storage= FirebaseStorage.getInstance();
        launcher=registerForActivityResult(new ActivityResultContracts.GetContent()
                , new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        imgbtn.setImageURI(result);

                        final StorageReference reference=storage.getReference()
                                .child("AskCommunity");

                        reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        temp=uri;
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
                });
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar2.setVisibility(View.VISIBLE);
                launcher.launch("image/*");
            }
        });
//        imgbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//            }
//        });

        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        try {
                            long timestamp = System.currentTimeMillis();

                            //get current user uid, since user is registered so we can get now

                            //setup data to add in db
                            progressBar2.setVisibility(View.VISIBLE);
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("commdisc", disc.getText().toString());
                            hashMap.put("commimage", temp.toString());
                            hashMap.put("commques", question.getText().toString());


                            //set data to db
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AskCommunity");
                            ref.child(uid)
                                    .setValue(hashMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            progressBar2.setVisibility(View.GONE);
                                            //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                                            Toast.makeText(getActivity(), "Profile inserted", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(Exception e) {
                                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }catch (Exception e)
                        {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });*/
        return view;
    }

}