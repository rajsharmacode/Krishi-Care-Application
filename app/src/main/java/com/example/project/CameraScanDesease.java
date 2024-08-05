package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.ml.Rajmodel;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CameraScanDesease extends AppCompatActivity {

    ImageView scanimg;
    Button scanbtn;
    int imageSize=224;
    TextView t1;

    ImageView backimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_scan_desease);

        scanimg=findViewById(R.id.scanimg);
        scanbtn=findViewById(R.id.scanbtn);
        t1=findViewById(R.id.scantext);
        backimg=findViewById(R.id.notifyback);


        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t1.setVisibility(View.GONE);

        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ImagePicker.Companion.with(CameraScanDesease.this)
//                        .start();



                if(checkSelfPermission(android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
                {
                    Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent,1);
//                    ImagePicker.Companion.with(CameraScanDesease.this)
////                            .crop()
//                            .start(1);

                }else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},1);
                }
            }
        });


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//        if (resultCode == RESULT_OK && requestCode == 1 && data != null) {
//            // Get the selected image URI
//            Uri imageUri = data.getData();
//
//            try {
//                // Convert the image URI to a Bitmap
//                Bitmap image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
//
//                int dimension = Math.min(image.getWidth(), image.getHeight());
//                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
//                scanimg.setImageBitmap(image);
//                classifyImage(image);
//                // You can also resize or process the bitmap as needed
//                // Make sure to handle exceptions like OutOfMemoryError if you're working with large images
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }

//        Uri image=data.getData();
//        scanimg.setImageURI(u);
//        Bitmap image=(Bitmap) data.getExtras().get("data");
//            int dimension = Math.min(image.getWidth(),image.getHeight());
//            image= ThumbnailUtils.extractThumbnail(image,dimension,dimension);
//            scanimg.setImageBitmap(image);

//            image=Bitmap.createScaledBitmap(image,imageSize,imageSize,false);
//            classifyImage(image);
//    }
        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            Bitmap image=(Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(),image.getHeight());
            image= ThumbnailUtils.extractThumbnail(image,dimension,dimension);
            scanimg.setImageBitmap(image);

            image=Bitmap.createScaledBitmap(image,imageSize,imageSize,false);
            classifyImage(image);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
    private void classifyImage(Bitmap image) {

        try {
            Rajmodel model = Rajmodel.newInstance(getApplicationContext());
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            // get 1D array of 224 * 224 pixels in image
            int [] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

//            int [] intValues = new int[imageSize * imageSize];
//            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            // iterate over pixels and extract R, G, and B values. Add to bytebuffer.
            int pixel = 0;
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++]; // RGB
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            Rajmodel.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();
            // find the index of the class with the biggest confidence.
            int maxPos = 0;
            float maxConfidence = 0;
            for(int i = 0; i < confidences.length; i++){
                if(confidences[i] > maxConfidence){
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }

            String[] classes = { "Corn Cercospora leaf spot Gray leaf spot",
                    "Apple Apple scab",
                    "Cherry Powdery mildew",
                    "Grape Black rot",
                    "Orange Haunglongbing (Citrus greening)",
                    "Potato Early blight",
                    "Tomato Bacterial spot",
                    "Strawberry Leaf scorch",
                    "Pepper bell Bacterial spot",
                    "Peach Bacterial spot",
                    "Orange Haunglongbing",
                    "Grape Esca (Black_Measles)"};
            scanbtn.setVisibility(View.GONE);
            t1.setVisibility(View.VISIBLE);
            t1.setText(classes[maxPos]);
            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String searchText = t1.getText().toString();
                    try {
                        searchText = URLEncoder.encode(searchText, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
//                    String s="https://www.google.com/search?g="+searchText;
                    String url = "https://www.google.com/search?q=" + searchText;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(s)));

                }
            });
            model.close();

        }catch(IOException e){
            //Todo Handle the exception
        }
    }
}