package com.example.project;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.project.databinding.ActivityMainBinding;
import com.example.project.databinding.ActivityProfilePageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilePage extends AppCompatActivity {

    ImageView img2;
    CircleImageView profileimage;
    ActivityProfilePageBinding binding;
    ActivityResultLauncher<String> launcher;
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reference;
    FirebaseUser user;
    EditText proname,pronumber,proemail;
    Spinner countrySpinner;
    Button savebtn;
    TextView txt2,txt11;
    String numberkeliye;
    String uid;
    Uri temp;
    ArrayAdapter<String> adapter;

    ProgressBar progressBarprofile;
    ProgressBar progressBarbackground;

    String proimage1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        img2=findViewById(R.id.imageView5);
        profileimage=findViewById(R.id.profile_image);
        proname=findViewById(R.id.proname);
        pronumber=findViewById(R.id.pronumber);
        proemail=findViewById(R.id.proemail);
        savebtn=findViewById(R.id.savebtn);
        txt2=findViewById(R.id.txt2);
        countrySpinner=findViewById(R.id.prostate);
        progressBarprofile=findViewById(R.id.progressBar);
        progressBarbackground=findViewById(R.id.progressBar3);

        user=FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        spinnerStateList();

        reference=FirebaseDatabase.getInstance().getReference();
        reference.child("Profileinfo").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                numberkeliye=dataSnapshot.getValue().toString();
//                txt11.setText(numberkeliye);

                                                                                                                                        reference.child("Profileinfo").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String proname1=dataSnapshot.child(uid).child("proname").getValue(String.class);
                        String pronumber1=dataSnapshot.child(uid).child("pronumber").getValue(String.class);
                        String proemail1=dataSnapshot.child(uid).child("proemail").getValue(String.class);
                        proimage1=dataSnapshot.child(uid).child("proimage").getValue(String.class);
                        String prostate1=dataSnapshot.child(uid).child("prostate").getValue(String.class);


                        proname.setText(proname1);
                        pronumber.setText(pronumber1);
                        proemail.setText(proemail1);
                        int defaultIndex1 = adapter.getPosition(prostate1);
                        countrySpinner.setSelection(defaultIndex1);
                        if(proimage1!=null)
                        {
                            Glide.with(getApplicationContext()).load(proimage1).into(profileimage);//

                        }
//                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(ProfilePage.this, "profilechlagya", Toast.LENGTH_SHORT).show();

                        progressBarbackground.setVisibility(View.GONE);
//                if(dataSnapshot.exists())
//                {
//                        Map map=(Map) dataSnapshot.getValue();
//                        txt2.setText(map.toString());
//                    txt2.setText(dataSnapshot.child("rar").getKey().toString());
//                    System.out.println(dataSnapshot.child("rar").getKey());

                        //    txt11.setText(map.toString());



//                    if(dataSnapshot.getKey().matches("rar"))
//                    {
//                        txt2.setText("raj"+map.toString());
//                        proname.setText(map.get("proname").toString());
//                        pronumber.setText(map.get("pronumber").toString());
//                        proemail.setText(map.get("proemail").toString());
//                        countrySpinner.setSelection(5);
//                        Toast.makeText(ProfilePage.this, map.get("proname").toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//                String no=getIntent().getStringExtra("n3").toString();
                        //  String phonenumber=getIntent().getStringExtra("mobile").toString();



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(ProfilePage.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//
//        txt11.setText(numberkeliye+"ujbo");



progressBarprofile.setVisibility(View.GONE);
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        launcher=registerForActivityResult(new ActivityResultContracts.GetContent()
                , new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {

                        if (result != null) {
                            profileimage.setImageURI(result);
                            progressBarprofile.setVisibility(View.VISIBLE);

                            final StorageReference reference = storage.getReference()
                                    .child("ProfileImage");

                            reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            temp = uri;
                                            progressBarprofile.setVisibility(View.GONE);

//                                        database.getReference().child("Profileinfo").child(uid).child("proimage")
//                                                .setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                    @Override
//                                                    public void onSuccess(Void unused) {
//                                                        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
//                                                    }
//                                                });
                                        }
                                    })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    progressBarprofile.setVisibility(View.GONE);
                                                    Toast.makeText(ProfilePage.this, "Failed to uplode image", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            });
                        }
                    }
                });
        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch("image/*");
            }
        });


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    if (proname.getText().toString().isEmpty()) {
                        proname.setError("Enter name");
                        progressBarbackground.setVisibility(View.GONE);

//                    } else if (Integer.parseInt(pronumber.getText().toString()) == 10 ) {
                    } else if (pronumber.getText().toString().length() != 10 ) {
                        progressBarbackground.setVisibility(View.GONE);

                        pronumber.setError("Enter Correct Number");
                    } else if (!proemail.getText().toString().contains("@gmail.com") || proemail.getText().toString().isEmpty()) {
                        progressBarbackground.setVisibility(View.GONE);

                        proemail.setError("Ptter Mismatch");
                    } else
                    {
                        progressBarbackground.setVisibility(View.VISIBLE);

                        long timestamp = System.currentTimeMillis();

                        //get current user uid, since user is registered so we can get now

                        String uidd=user.getUid();
                        //setup data to add in db
//                    String tt;
//                    if(temp==null)
//                    {
//                        tt=temp.toString();
//                    }
//                    else
//                    {
//                        tt=proimage1;
//                    }
//                    progressBar.setVisibility(View.GONE);

                        if(temp==null)
                        {

                            //temp.toString()=proimage1;
//                        Toast.makeText(ProfilePage.this, "select image", Toast.LENGTH_SHORT).show();
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("proname", proname.getText().toString());
                            hashMap.put("proemail", proemail.getText().toString());
                            hashMap.put("pronumber", pronumber.getText().toString());
                            hashMap.put("prostate", countrySpinner.getSelectedItem().toString());
                            hashMap.put("proimage", proimage1);
                            hashMap.put("userkey", uidd);


                            //set data to db
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Profileinfo");
                            ref.child(uid)
                                    .setValue(hashMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            progressBarbackground.setVisibility(View.GONE);
                                            //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                                            Toast.makeText(ProfilePage.this, "Profile inserted", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(Exception e) {
                                            progressBarbackground.setVisibility(View.GONE);
                                            Toast.makeText(ProfilePage.this, "rrr"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }else {
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("proname", proname.getText().toString());
                            hashMap.put("proemail", proemail.getText().toString());
                            hashMap.put("pronumber", pronumber.getText().toString());
                            hashMap.put("prostate", countrySpinner.getSelectedItem().toString());
                            hashMap.put("proimage", temp.toString());
                            hashMap.put("userkey", uidd);


                            //set data to db
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Profileinfo");
                            ref.child(uid)
                                    .setValue(hashMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            progressBarbackground.setVisibility(View.GONE);
                                            //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                                            Toast.makeText(ProfilePage.this, "Profile inserted1", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(Exception e) {
                                            progressBarbackground.setVisibility(View.GONE);
                                            Toast.makeText(ProfilePage.this, "rrr"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }

                    }




                }catch (Exception e)
                {
                    Toast.makeText(ProfilePage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

//    private void profileImageAlloperation() {
//
//
//      //////////////// Retrive image from database
//        database.getReference()
//                .child("raja").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String img1=dataSnapshot.getValue(String.class);
////                        Glide.with(holder.productimage.getContext()).load(modelHomepage.getImage()).into(holder.productimage);
//
//                        if(dataSnapshot.exists()) {
//
//
//                            Picasso.get()
//                                    .load(img1)
//                                    .into(profileimage);
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

    private void spinnerStateList() {
        String[] countryCodes = {"Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"};

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryCodes);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);
        int defaultIndex = adapter.getPosition("Gujarat");
        countrySpinner.setSelection(defaultIndex);

    }
}