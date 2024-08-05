package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorPage extends AppCompatActivity {

    EditText edN,edP,edK,ednumber;
    TextView txtn,txtp,txtk;
    Button btncalculate,btnAdd,btnSubtract;
    ImageButton btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_page);



        edN=findViewById(R.id.edN);
        edP=findViewById(R.id.edP);
        edK=findViewById(R.id.edK);
        ednumber=findViewById(R.id.ednumber);
        btncalculate=findViewById(R.id.btncalculate);
        Spinner cropSpinner = findViewById(R.id.crop_spinner);

        btnback=findViewById(R.id.btnback);
        txtn=findViewById(R.id.txtn);
        txtk=findViewById(R.id.txtk);
        txtp=findViewById(R.id.txtp);
        btnAdd=findViewById(R.id.plus);
        btnSubtract=findViewById(R.id.minus);
        RadioButton unit = findViewById(R.id.unit);



        unit.setChecked(true);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    String input=ednumber.getText().toString();
                    if (input.isEmpty()){
                        double n=0;

                        ednumber.setText(String.valueOf(n + 0.5));
                        ednumber.setSelection(ednumber.getText().length());
                    }else {
                        double n = Double.parseDouble(input);

                        ednumber.setText(String.valueOf(n + 0.5));
                        ednumber.setSelection(ednumber.getText().length());
                    }



                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"please provide number "+e,Toast.LENGTH_SHORT).show();
                }
            }
        });



        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String input=ednumber.getText().toString();
                    if (!input.isEmpty()){
                        double currentValue = Double.parseDouble(input);
                        if (currentValue > 0) {
                            ednumber.setText(String.valueOf(currentValue - 0.5));
                            ednumber.setSelection(ednumber.getText().length());

                        }
                    }else {

                        Toast.makeText(getApplicationContext(),"value cannot be negative ",Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"please first provide number "+e,Toast.LENGTH_SHORT).show();
                }

            }
        });
        /*btncalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String N=edN.getText().toString();
                    String P=edP.getText().toString();
                    String K=edK.getText().toString();

                    String input=ednumber.getText().toString();

                    double number = Double.parseDouble(input);

                    double p1 = Double.parseDouble(P);
                    double Ppergram=0.2*p1;
                    double totalP1=Ppergram*10;
                    double totalP=totalP1*number;
                    String formateP=String.format("%.2f",totalP);

                    txtp.setText(formateP+" Kg");

                    double k1 = Double.parseDouble(K);
                    double Kpergram=0.16*k1;
                    double totalK1=Kpergram*10;
                    double totalK=totalK1*number;
                    String formateK=String.format("%.2f",totalK);
                    txtk.setText(formateK+" Kg");

                    double n1 = Double.parseDouble(N);
                    double Npergram=0.15*n1;
                    double totalN1=Npergram*10;
                    double totalN=totalN1*number;
                    String formateN=String.format("%.2f",totalN);
                    txtn.setText(formateN+" Kg");




                    //txtn.setText(number+"Kg");
                    //Toast.makeText(MainActivity.this,"Double="+number,Toast.LENGTH_SHORT).show();


                }catch (Exception e){
                    Toast.makeText(MainActivity.this, " "+e, Toast.LENGTH_LONG).show();
                }
                 }
        });*/


        //Spinner


        Spinner croSpinner = findViewById(R.id.crop_spinner);
        String[] cropCategories = {"Cabbage", "Cucumber", "Brinjal", "Okra", "Onion","Pigeon Pea","Peanut","Potato","Rice","Tomato","Soybean","Wheat"};

        ArrayAdapter<String> cropAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cropCategories);
        cropAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cropSpinner.setAdapter(cropAdapter);
        try {

            cropSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String selectedCropCategory = cropCategories[position];
                    if (selectedCropCategory.equals("Cabbage")) {

                        txtp.setText("");
                        txtk.setText("");
                        txtn.setText("");

                        btncalculate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String N=edN.getText().toString();
                                    String P=edP.getText().toString();
                                    String K=edK.getText().toString();

                                    String input=ednumber.getText().toString();

                                    double number = Double.parseDouble(input);

                                    double p1 = Double.parseDouble(P);
                                    double Ppergram=0.2*p1;
                                    double totalP1=Ppergram*10;
                                    double totalP=totalP1*number;
                                    String formateP=String.format("%.2f",totalP);

                                    txtp.setText(formateP+" Kg");

                                    double k1 = Double.parseDouble(K);
                                    double Kpergram=0.16*k1;
                                    double totalK1=Kpergram*10;
                                    double totalK=totalK1*number;
                                    String formateK=String.format("%.2f",totalK);
                                    txtk.setText(formateK+" Kg");

                                    double n1 = Double.parseDouble(N);
                                    double Npergram=0.15*n1;
                                    double totalN1=Npergram*10;
                                    double totalN=totalN1*number;
                                    String formateN=String.format("%.2f",totalN);
                                    txtn.setText(formateN+" Kg");




                                    //txtn.setText(number+"Kg");
                                    //Toast.makeText(MainActivity.this,"Double="+number,Toast.LENGTH_SHORT).show();


                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), " "+e, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                    else if (selectedCropCategory.equals("Cucumber")) {
                        txtp.setText("");
                        txtk.setText("");
                        txtn.setText("");
                        btncalculate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String N=edN.getText().toString();
                                    String P=edP.getText().toString();
                                    String K=edK.getText().toString();

                                    String input=ednumber.getText().toString();

                                    double number = Double.parseDouble(input);

                                    double p1 = Double.parseDouble(P);
                                    double Ppergram=0.21*p1;
                                    double totalP1=Ppergram*10;
                                    double totalP=totalP1*number;
                                    String formateP=String.format("%.2f",totalP);

                                    txtp.setText(formateP+" Kg");

                                    double k1 = Double.parseDouble(K);
                                    double Kpergram=0.15*k1;
                                    double totalK1=Kpergram*10;
                                    double totalK=totalK1*number;
                                    String formateK=String.format("%.2f",totalK);
                                    txtk.setText(formateK+" Kg");

                                    double n1 = Double.parseDouble(N);
                                    double Npergram=0.16*n1;
                                    double totalN1=Npergram*10;
                                    double totalN=totalN1*number;
                                    String formateN=String.format("%.2f",totalN);
                                    txtn.setText(formateN+" Kg");




                                    //txtn.setText(number+"Kg");
                                    //Toast.makeText(MainActivity.this,"Double="+number,Toast.LENGTH_SHORT).show();


                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), " "+e, Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                    else if (selectedCropCategory.equals("Brinjal")) {
                        txtp.setText("");
                        txtk.setText("");
                        txtn.setText("");
                        btncalculate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String N=edN.getText().toString();
                                    String P=edP.getText().toString();
                                    String K=edK.getText().toString();

                                    String input=ednumber.getText().toString();

                                    double number = Double.parseDouble(input);

                                    double p1 = Double.parseDouble(P);
                                    double Ppergram=0.22*p1;
                                    double totalP1=Ppergram*10;
                                    double totalP=totalP1*number;
                                    String formateP=String.format("%.2f",totalP);

                                    txtp.setText(formateP+" Kg");

                                    double k1 = Double.parseDouble(K);
                                    double Kpergram=0.17*k1;
                                    double totalK1=Kpergram*10;
                                    double totalK=totalK1*number;
                                    String formateK=String.format("%.2f",totalK);
                                    txtk.setText(formateK+" Kg");

                                    double n1 = Double.parseDouble(N);
                                    double Npergram=0.17*n1;
                                    double totalN1=Npergram*10;
                                    double totalN=totalN1*number;
                                    String formateN=String.format("%.2f",totalN);
                                    txtn.setText(formateN+" Kg");




                                    //txtn.setText(number+"Kg");
                                    //Toast.makeText(MainActivity.this,"Double="+number,Toast.LENGTH_SHORT).show();


                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), " "+e, Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                    else if (selectedCropCategory.equals("Okra")) {
                        txtp.setText("");
                        txtk.setText("");
                        txtn.setText("");
                        btncalculate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String N=edN.getText().toString();
                                    String P=edP.getText().toString();
                                    String K=edK.getText().toString();

                                    String input=ednumber.getText().toString();

                                    double number = Double.parseDouble(input);

                                    double p1 = Double.parseDouble(P);
                                    double Ppergram=0.21*p1;
                                    double totalP1=Ppergram*10;
                                    double totalP=totalP1*number;
                                    String formateP=String.format("%.2f",totalP);

                                    txtp.setText(formateP+" Kg");

                                    double k1 = Double.parseDouble(K);
                                    double Kpergram=0.18*k1;
                                    double totalK1=Kpergram*10;
                                    double totalK=totalK1*number;
                                    String formateK=String.format("%.2f",totalK);
                                    txtk.setText(formateK+" Kg");

                                    double n1 = Double.parseDouble(N);
                                    double Npergram=0.16*n1;
                                    double totalN1=Npergram*10;
                                    double totalN=totalN1*number;
                                    String formateN=String.format("%.2f",totalN);
                                    txtn.setText(formateN+" Kg");




                                    //txtn.setText(number+"Kg");
                                    //Toast.makeText(MainActivity.this,"Double="+number,Toast.LENGTH_SHORT).show();


                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), " "+e, Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                    else if (selectedCropCategory.equals("Onion")) {
                        txtp.setText("");
                        txtk.setText("");
                        txtn.setText("");
                        btncalculate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String N=edN.getText().toString();
                                    String P=edP.getText().toString();
                                    String K=edK.getText().toString();

                                    String input=ednumber.getText().toString();

                                    double number = Double.parseDouble(input);

                                    double p1 = Double.parseDouble(P);
                                    double Ppergram=0.22*p1;
                                    double totalP1=Ppergram*10;
                                    double totalP=totalP1*number;
                                    String formateP=String.format("%.2f",totalP);

                                    txtp.setText(formateP+" Kg");

                                    double k1 = Double.parseDouble(K);
                                    double Kpergram=0.17*k1;
                                    double totalK1=Kpergram*10;
                                    double totalK=totalK1*number;
                                    String formateK=String.format("%.2f",totalK);
                                    txtk.setText(formateK+" Kg");

                                    double n1 = Double.parseDouble(N);
                                    double Npergram=0.15*n1;
                                    double totalN1=Npergram*10;
                                    double totalN=totalN1*number;
                                    String formateN=String.format("%.2f",totalN);
                                    txtn.setText(formateN+" Kg");




                                    //txtn.setText(number+"Kg");
                                    //Toast.makeText(MainActivity.this,"Double="+number,Toast.LENGTH_SHORT).show();


                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), " "+e, Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                    else if (selectedCropCategory.equals("Pigeon Pea")) {
                        txtp.setText("");
                        txtk.setText("");
                        txtn.setText("");
                        btncalculate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String N=edN.getText().toString();
                                    String P=edP.getText().toString();
                                    String K=edK.getText().toString();

                                    String input=ednumber.getText().toString();

                                    double number = Double.parseDouble(input);

                                    double p1 = Double.parseDouble(P);
                                    double Ppergram=0.20*p1;
                                    double totalP1=Ppergram*10;
                                    double totalP=totalP1*number;
                                    String formateP=String.format("%.2f",totalP);

                                    txtp.setText(formateP+" Kg");

                                    double k1 = Double.parseDouble(K);
                                    double Kpergram=0.15*k1;
                                    double totalK1=Kpergram*10;
                                    double totalK=totalK1*number;
                                    String formateK=String.format("%.2f",totalK);
                                    txtk.setText(formateK+" Kg");

                                    double n1 = Double.parseDouble(N);
                                    double Npergram=0.15*n1;
                                    double totalN1=Npergram*10;
                                    double totalN=totalN1*number;
                                    String formateN=String.format("%.2f",totalN);
                                    txtn.setText(formateN+" Kg");




                                    //txtn.setText(number+"Kg");
                                    //Toast.makeText(MainActivity.this,"Double="+number,Toast.LENGTH_SHORT).show();


                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), " "+e, Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                    else if (selectedCropCategory.equals("Peanut")) {
                        txtp.setText("");
                        txtk.setText("");
                        txtn.setText("");
                        btncalculate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String N=edN.getText().toString();
                                    String P=edP.getText().toString();
                                    String K=edK.getText().toString();

                                    String input=ednumber.getText().toString();

                                    double number = Double.parseDouble(input);

                                    double p1 = Double.parseDouble(P);
                                    double Ppergram=0.19*p1;
                                    double totalP1=Ppergram*10;
                                    double totalP=totalP1*number;
                                    String formateP=String.format("%.2f",totalP);

                                    txtp.setText(formateP+" Kg");

                                    double k1 = Double.parseDouble(K);
                                    double Kpergram=0.18*k1;
                                    double totalK1=Kpergram*10;
                                    double totalK=totalK1*number;
                                    String formateK=String.format("%.2f",totalK);
                                    txtk.setText(formateK+" Kg");

                                    double n1 = Double.parseDouble(N);
                                    double Npergram=0.12*n1;
                                    double totalN1=Npergram*10;
                                    double totalN=totalN1*number;
                                    String formateN=String.format("%.2f",totalN);
                                    txtn.setText(formateN+" Kg");




                                    //txtn.setText(number+"Kg");
                                    //Toast.makeText(MainActivity.this,"Double="+number,Toast.LENGTH_SHORT).show();


                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), " "+e, Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                    else if (selectedCropCategory.equals("Potato")) {
                        txtp.setText("");
                        txtk.setText("");
                        txtn.setText("");
                        btncalculate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String N=edN.getText().toString();
                                    String P=edP.getText().toString();
                                    String K=edK.getText().toString();

                                    String input=ednumber.getText().toString();

                                    double number = Double.parseDouble(input);

                                    double p1 = Double.parseDouble(P);
                                    double Ppergram=0.27*p1;
                                    double totalP1=Ppergram*10;
                                    double totalP=totalP1*number;
                                    String formateP=String.format("%.2f",totalP);

                                    txtp.setText(formateP+" Kg");

                                    double k1 = Double.parseDouble(K);
                                    double Kpergram=0.19*k1;
                                    double totalK1=Kpergram*10;
                                    double totalK=totalK1*number;
                                    String formateK=String.format("%.2f",totalK);
                                    txtk.setText(formateK+" Kg");

                                    double n1 = Double.parseDouble(N);
                                    double Npergram=0.16*n1;
                                    double totalN1=Npergram*10;
                                    double totalN=totalN1*number;
                                    String formateN=String.format("%.2f",totalN);
                                    txtn.setText(formateN+" Kg");




                                    //txtn.setText(number+"Kg");
                                    //Toast.makeText(MainActivity.this,"Double="+number,Toast.LENGTH_SHORT).show();


                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), " "+e, Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                    else if (selectedCropCategory.equals("Rice")) {
                        txtp.setText("");
                        txtk.setText("");
                        txtn.setText("");
                        btncalculate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String N=edN.getText().toString();
                                    String P=edP.getText().toString();
                                    String K=edK.getText().toString();

                                    String input=ednumber.getText().toString();

                                    double number = Double.parseDouble(input);

                                    double p1 = Double.parseDouble(P);
                                    double Ppergram=0.28*p1;
                                    double totalP1=Ppergram*10;
                                    double totalP=totalP1*number;
                                    String formateP=String.format("%.2f",totalP);

                                    txtp.setText(formateP+" Kg");

                                    double k1 = Double.parseDouble(K);
                                    double Kpergram=0.20*k1;
                                    double totalK1=Kpergram*10;
                                    double totalK=totalK1*number;
                                    String formateK=String.format("%.2f",totalK);
                                    txtk.setText(formateK+" Kg");

                                    double n1 = Double.parseDouble(N);
                                    double Npergram=0.21*n1;
                                    double totalN1=Npergram*10;
                                    double totalN=totalN1*number;
                                    String formateN=String.format("%.2f",totalN);
                                    txtn.setText(formateN+" Kg");




                                    //txtn.setText(number+"Kg");
                                    //Toast.makeText(MainActivity.this,"Double="+number,Toast.LENGTH_SHORT).show();


                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), " "+e, Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                    else if (selectedCropCategory.equals("Tomato")) {
                        txtp.setText("");
                        txtk.setText("");
                        txtn.setText("");
                        btncalculate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String N=edN.getText().toString();
                                    String P=edP.getText().toString();
                                    String K=edK.getText().toString();

                                    String input=ednumber.getText().toString();

                                    double number = Double.parseDouble(input);

                                    double p1 = Double.parseDouble(P);
                                    double Ppergram=0.21*p1;
                                    double totalP1=Ppergram*10;
                                    double totalP=totalP1*number;
                                    String formateP=String.format("%.2f",totalP);

                                    txtp.setText(formateP+" Kg");

                                    double k1 = Double.parseDouble(K);
                                    double Kpergram=0.16*k1;
                                    double totalK1=Kpergram*10;
                                    double totalK=totalK1*number;
                                    String formateK=String.format("%.2f",totalK);
                                    txtk.setText(formateK+" Kg");

                                    double n1 = Double.parseDouble(N);
                                    double Npergram=0.17*n1;
                                    double totalN1=Npergram*10;
                                    double totalN=totalN1*number;
                                    String formateN=String.format("%.2f",totalN);
                                    txtn.setText(formateN+" Kg");




                                    //txtn.setText(number+"Kg");
                                    //Toast.makeText(MainActivity.this,"Double="+number,Toast.LENGTH_SHORT).show();


                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), " "+e, Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                    else if (selectedCropCategory.equals("Soybean")) {
                        txtp.setText("");
                        txtk.setText("");
                        txtn.setText("");
                        btncalculate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String N=edN.getText().toString();
                                    String P=edP.getText().toString();
                                    String K=edK.getText().toString();

                                    String input=ednumber.getText().toString();

                                    double number = Double.parseDouble(input);

                                    double p1 = Double.parseDouble(P);
                                    double Ppergram=0.21*p1;
                                    double totalP1=Ppergram*10;
                                    double totalP=totalP1*number;
                                    String formateP=String.format("%.2f",totalP);

                                    txtp.setText(formateP+" Kg");

                                    double k1 = Double.parseDouble(K);
                                    double Kpergram=0.15*k1;
                                    double totalK1=Kpergram*10;
                                    double totalK=totalK1*number;
                                    String formateK=String.format("%.2f",totalK);
                                    txtk.setText(formateK+" Kg");

                                    double n1 = Double.parseDouble(N);
                                    double Npergram=0.16*n1;
                                    double totalN1=Npergram*10;
                                    double totalN=totalN1*number;
                                    String formateN=String.format("%.2f",totalN);
                                    txtn.setText(formateN+" Kg");




                                    //txtn.setText(number+"Kg");
                                    //Toast.makeText(MainActivity.this,"Double="+number,Toast.LENGTH_SHORT).show();


                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), " "+e, Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                    else if (selectedCropCategory.equals("Wheat")) {
                        txtp.setText("");
                        txtk.setText("");
                        txtn.setText("");
                        btncalculate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String N=edN.getText().toString();
                                    String P=edP.getText().toString();
                                    String K=edK.getText().toString();

                                    String input=ednumber.getText().toString();

                                    double number = Double.parseDouble(input);

                                    double p1 = Double.parseDouble(P);
                                    double Ppergram=0.221*p1;
                                    double totalP1=Ppergram*10;
                                    double totalP=totalP1*number;
                                    String formateP=String.format("%.2f",totalP);

                                    txtp.setText(formateP+" Kg");

                                    double k1 = Double.parseDouble(K);
                                    double Kpergram=0.151*k1;
                                    double totalK1=Kpergram*10;
                                    double totalK=totalK1*number;
                                    String formateK=String.format("%.2f",totalK);
                                    txtk.setText(formateK+" Kg");

                                    double n1 = Double.parseDouble(N);
                                    double Npergram=0.167*n1;
                                    double totalN1=Npergram*10;
                                    double totalN=totalN1*number;
                                    String formateN=String.format("%.2f",totalN);
                                    txtn.setText(formateN+" Kg");




                                    //txtn.setText(number+"Kg");
                                    //Toast.makeText(MainActivity.this,"Double="+number,Toast.LENGTH_SHORT).show();


                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), " "+e, Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Please First Select any crop",Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // Do nothing
                }
            });
        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"Error "+e,Toast.LENGTH_LONG).show();
        }






}
}