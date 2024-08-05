package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CaseonDelevery extends AppCompatActivity {
    TextView codorderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caseon_delevery);

        codorderid=findViewById(R.id.codorderid);
        String orderidkey=getIntent().getStringExtra("itemid");
        codorderid.setText("Order ID - "+orderidkey);

    }
}