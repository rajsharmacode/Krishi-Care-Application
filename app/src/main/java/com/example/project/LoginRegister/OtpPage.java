package com.example.project.LoginRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpPage extends AppCompatActivity {

    EditText otp1,otp2,otp3,otp4,otp5,otp6;
    Button btn;

    String allotp;

    String phonenumber;
    String otpid;
    TextView rrr;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);

//        otp1=findViewById(R.id.otp_edt1);
//        otp2=findViewById(R.id.otp_edt2);
//        otp3=findViewById(R.id.otp_edt3);
//        otp4=findViewById(R.id.otp_edt4);
//        otp5=findViewById(R.id.otp_edt5);
//        otp6=findViewById(R.id.otp_edt6);
//
//        btn=findViewById(R.id.btn_Verify);
//
//        rrr=findViewById(R.id.rrr11);
//
//
//        phonenumber=getIntent().getStringExtra("mobile").toString();
//        mAuth=FirebaseAuth.getInstance();
//
////        phonenumber="6352517277";
//
//
////        otp1.addTextChangedListener(new TextWatcher() {
////            @Override
////            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////
////            }
////
////            @Override
////            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
////                if (!s.toString().trim().isEmpty()){
////                    otp2.requestFocus();
////                }
////            }
////
////            @Override
////            public void afterTextChanged(Editable editable) {
////
////            }
////        });
//        initiateotp();
//
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                allotp=otp1.getText().toString()+
//                        otp2.getText().toString()+
//                        otp3.getText().toString()+
//                        otp4.getText().toString()+
//                        otp5.getText().toString()+
//                        otp6.getText().toString();
//
////                allotp.trim();
////                Toast.makeText(OtpPage.this, allotp, Toast.LENGTH_SHORT).show();
//
//
//                if(allotp.isEmpty())
//                    Toast.makeText(getApplicationContext(),"Blank Field can not be processed",Toast.LENGTH_LONG).show();
//                else if(allotp.length()!=6)
//                    Toast.makeText(getApplicationContext(),"INvalid OTP",Toast.LENGTH_LONG).show();
//                else
//                {
//                    PhoneAuthCredential credential= PhoneAuthProvider.getCredential(otpid,allotp);
//                    signInWithPhoneAuthCredential(credential);
//                }
//
//
//            }
//        });


    }

//    private void initiateotp()
//    {
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                phonenumber,        // Phone number to verify
//                60,                 // Timeout duration
//                TimeUnit.SECONDS,   // Unit of timeout
//                this,               // Activity (for callback binding)
//                new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
//                {
//                    @Override
//                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken)
//                    {
//                        otpid=s;
//                    }
//
//                    @Override
//                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
//                    {
//                        signInWithPhoneAuthCredential(phoneAuthCredential);
//                    }
//
//                    @Override
//                    public void onVerificationFailed(FirebaseException e) {
//                        rrr.setText(e.getMessage());
//                        Toast.makeText(getApplicationContext(),"rajj"+e.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//                });        // OnVerificationStateChangedCallbacks
//
//    }
//
//    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful())
//                        {
//                            startActivity(new Intent(OtpPage.this,MainActivity.class));
//                            finish();
//
//                        } else {
//                            Toast.makeText(getApplicationContext(),"Signin Code Error",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//    }
}