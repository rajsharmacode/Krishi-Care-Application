package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CaltivationFullPage extends AppCompatActivity {

    TextView readalldata,readtitle;
    Button moredatabtn;
    ImageView readimgdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caltivation_full_page);



        String Readdata=getIntent().getStringExtra("readdata");
        String Readimg=getIntent().getStringExtra("readimg");
        String Readname=getIntent().getStringExtra("readname");

        readalldata=findViewById(R.id.precresdisc);
        readtitle=findViewById(R.id.pracrestitle);
//        moredatabtn=findViewById(R.id.readmoredatabtn);
        readimgdata=findViewById(R.id.pracresimg);



        Glide.with(getApplicationContext()).load(Readimg).into(readimgdata);
        readalldata.setText("            "+Readdata);
        readtitle.setText(Readname);
    }
}