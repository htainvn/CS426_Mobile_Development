package com.example.intenthw2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    static final int REQUEST_IMAGE_GET = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cameraButton = findViewById(R.id.cameraButton);
        Button uploadButton = findViewById(R.id.uploadButton);
        Button mapButton = findViewById(R.id.mapButton);
        imageView = findViewById(R.id.imgv);

        /*Camera button setup -- Using Camera Intent*/
        cameraButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(openCamera, REQUEST_IMAGE_CAPTURE);
                }
                catch(ActivityNotFoundException e) {

                }
            }
        });

        /*Upload button setup -- Using Storage Intent*/
        uploadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent uploadImage = new Intent(Intent.ACTION_GET_CONTENT);
                uploadImage.setType("image/*");
                try {
                    startActivityForResult(uploadImage, REQUEST_IMAGE_GET);
                }
                catch(ActivityNotFoundException e) {

                }
            }
        });

        /*Map button setup -- Using Map Intent*/
        mapButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Uri store = Uri.parse("geo:14.48947279980611, 109.08928526833557?q=Noi%20that%20ly%20thuy");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(store);
                try {
                    startActivity(intent);
                }
                catch(ActivityNotFoundException e) {

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(image);
        }
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            imageView.setImageURI(data.getData());
        }
    }
}