package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        imageView=findViewById(R.id.imageView);


        Picasso.get().load("https://cdnuploads.aa.com.tr/uploads/Contents/2021/06/02/thumbs_b_c_6b3c3a9ab2d1ef24c4cfb763226ecec1.jpg?v=025740").into(imageView);


    }






}