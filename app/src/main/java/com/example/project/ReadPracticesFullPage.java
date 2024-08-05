package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ReadPracticesFullPage extends AppCompatActivity {
    TextView readalldata,readtitle;
    Button moredatabtn;
    ImageView readimgdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_practices_full_page);

        String Readdata=getIntent().getStringExtra("readdata");
        String Readimg=getIntent().getStringExtra("readimg");
        String Readname=getIntent().getStringExtra("readname");

        readalldata=findViewById(R.id.readalldata);
        readtitle=findViewById(R.id.readtitle);
        moredatabtn=findViewById(R.id.readmoredatabtn);
        readimgdata=findViewById(R.id.readallimg1);


        Glide.with(getApplicationContext()).load(Readimg).into(readimgdata);
        readalldata.setText("            "+Readdata);
        readtitle.setText(Readname);

        moredatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = Readname;
                try {
                    searchText = URLEncoder.encode(searchText, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
//                    String s="https://www.google.com/search?g="+searchText;
                String url = "https://www.google.com/search?q=" + searchText;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });


    }
}