package com.example.project.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.hbb20.CountryCodePicker;

public class LoginPage extends AppCompatActivity {


    CountryCodePicker ccp;
    EditText enterno;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

//        enterno=(EditText) findViewById(R.id.enter_mobile_number);
//        ccp=(CountryCodePicker)findViewById(R.id.ccp);
//        ccp.registerCarrierNumberEditText(enterno);
//        btn=findViewById(R.id.btn_GetOtp);
//
//
//
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String country_code=ccp.getSelectedCountryCode();
//
//
//                FirebaseApp.initializeApp(/*context=*/ LoginPage.this);
//                FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
//                firebaseAppCheck.installAppCheckProviderFactory(
//                        SafetyNetAppCheckProviderFactory.getInstance());
//
//                Toast.makeText(LoginPage.this, country_code, Toast.LENGTH_LONG).show();
////
//                Intent intent=new Intent(LoginPage.this,OtpPage.class);
//
////                Intent ii=new Intent(LoginPage.this,ProfilePage.class);
////                ii.putExtra("no",enterno.getText().toString());
//                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
//                intent.putExtra("no","6352517277");
//                startActivity(intent);
//            }
//        });
//
//


    }
}