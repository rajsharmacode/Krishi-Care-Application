package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project.FrontMainPages.CommunityFragment;
import com.example.project.FrontMainPages.HomeFragment;
import com.example.project.FrontMainPages.ReadFragment;
import com.example.project.FrontMainPages.ShopFragment;
import com.example.project.Models.AddtoCartModel;
import com.example.project.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    NavigationView navdrawer;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
//    ImageView menubtn;
//    TextView titleclr;

    TextView tooltitle,tooladnumber;
    DatabaseReference reference;

    ImageButton toolsearch,toolnotify,tooladtocart,toolmenubtn;

    ArrayList<AddtoCartModel> list;

    FirebaseUser user;
    String uid;

    String proname1;
    String proimage1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());

        /////////////
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar1);
//        Toolbar toolbar1=(Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);


        setContentView(binding.getRoot());




        navdrawer=(NavigationView)findViewById(R.id.navdrawer);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);

        tooltitle=findViewById(R.id.tooltitle);
        tooladnumber=findViewById(R.id.tooladnumber);
        toolsearch=findViewById(R.id.toolsearch);
        toolnotify=findViewById(R.id.toolnotify);
        tooladtocart=findViewById(R.id.tooladtocart);
        toolmenubtn=findViewById(R.id.toolmenubtn);



        ////////hide status bar
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolnotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),NotifyPage.class);
                startActivity(i);
            }
        });
        toolsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), SerachItemPage.class);
                startActivity(i);
            }
        });
        reference= FirebaseDatabase.getInstance().getReference();
        reference.child("AddtoCart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count=0;
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
                    {
//                    AddtoCartModel data=snapshot.getValue(AddtoCartModel.class);
//                    list.add(data);
                        count++;


                    }
                }
                tooladnumber.setText(String.valueOf(count));


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        tooladtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),AddtoCartPage.class);
                startActivity(i);
            }
        });

        toolmenubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });



//        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.stop);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();


        navdrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {

                int menuitem=menuItem.getItemId();

                if(menuitem== R.id.contactus)
                {
                    startActivity(new Intent(getApplicationContext(),ContactUs.class));

                    Toast.makeText(getApplicationContext(),"Contact",Toast.LENGTH_LONG).show();
                }
                  if(menuitem== R.id.aboutus)
                {
                    startActivity(new Intent(getApplicationContext(),AboutUsPage.class));

                    Toast.makeText(getApplicationContext(),"about",Toast.LENGTH_LONG).show();
                }
                  if(menuitem == R.id.logout)
                {
                    FirebaseAuth.getInstance().signOut();

                    Toast.makeText(getApplicationContext(),"Log Out",Toast.LENGTH_LONG).show();
                    finish();
                }
                if(menuitem == R.id.myorder)
                {

                    startActivity(new Intent(getApplicationContext(),MyorderPage.class));

                    Toast.makeText(getApplicationContext(),"myorder",Toast.LENGTH_LONG).show();
                }

                drawerLayout.close();
//                switch (menuitem)
//                {
//                    case 1 :
//                        Toast.makeText(getApplicationContext(),"Home Panel is Open",Toast.LENGTH_LONG).show();
//                        drawerLayout.close();
//                        break;
//
//                    case 2 :
//                        Toast.makeText(getApplicationContext(),"Call Panel is Open",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//
//                    case 3 :
//                        Toast.makeText(getApplicationContext(),"Setting Panel is Open",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                }

                return false;
            }
        });

        View viewitem=navdrawer.getHeaderView(0);
        ImageView personimg=viewitem.findViewById(R.id.personimg);
        TextView usernametxt=viewitem.findViewById(R.id.usernametxt);




        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        FirebaseDatabase.getInstance().getReference().child("Profileinfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                proname1=dataSnapshot.child(uid).child("proname").getValue(String.class);
                proimage1=dataSnapshot.child(uid).child("proimage").getValue(String.class);

                if(proname1==null || proimage1==null)
                {

                }else {
                    Glide.with(getApplicationContext()).load(proimage1).into(personimg);
                    usernametxt.setText(proname1);
                }
//                            myviewholder.personname.setText(proname1);
//                            Glide.with(myviewholder.personprofileimg.getContext()).load(model.getCommimage()).into(myviewholder.personprofileimg);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        Toast.makeText(getApplicationContext(), proimage1+proimage1+"", Toast.LENGTH_SHORT).show();


        personimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(MainActivity.this,ProfilePage.class);
                startActivity(i);
//                Toast.makeText(getApplicationContext(), usernametxt.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

//            Toast.makeText(getApplicationContext(), "hiidd"+item.getTitle(), Toast.LENGTH_SHORT).show();

            if (item.getTitle().equals("Home"))
            {
                replaceFragment(new HomeFragment());
//                Toast.makeText(getApplicationContext(), "hiii", Toast.LENGTH_LONG).show();
                tooltitle.setText("Krishi Care");
            }else if (item.getTitle().equals("Community"))
            {
                replaceFragment(new CommunityFragment());
                tooltitle.setText("Community");
            }

            else if (item.getTitle().equals("Shop"))
            {
                replaceFragment(new ShopFragment());
                tooltitle.setText("Shop Categories");
            }
            else if(item.getTitle().equals("Practice"))
            {
                replaceFragment(new ReadFragment());
                tooltitle.setText("Practices");
            }

//                switch(item.getItemId()){
//
//                case 2131230930 :
//                    replaceFragment(new HomeFragment());
//                    Toast.makeText(getApplicationContext(), "hiii", Toast.LENGTH_LONG).show();
//                    break;
//
//                case 2131230842:
//                    replaceFragment(new CommunityFragment());
//                    break;
//
//                case 2131231112:
//                    replaceFragment(new ShopFragment());
//                    break;
//
//                case 2131231077:
//                    replaceFragment(new ReadFragment());
//                    break;
//            }
            return true;
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.upper_all_icon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to close the app?\n\n")
                .setTitle("Exit app")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.upper_all_icon,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
}