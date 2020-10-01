package com.example.uber.adminpackages;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uber.R;

public class AnotherActivity extends AppCompatActivity {
    ImageView mImageIv;
    TextView mtitleTv, mdescTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        ActionBar actionBar = getSupportActionBar();
        mImageIv = findViewById(R.id.imageView);
        mtitleTv = findViewById(R.id.title);
        mdescTv = findViewById(R.id.description);

        Intent intent = getIntent();
        String mTitle = intent.getStringExtra("iTitle");
        String mDescription = intent.getStringExtra("iDescription");
        byte[] mbytes = getIntent().getByteArrayExtra("iImage");

        Bitmap bitmap = BitmapFactory.decodeByteArray(mbytes,0,mbytes.length);
        actionBar.setTitle(mTitle);

        mtitleTv.setText(mTitle);
        mdescTv.setText(mDescription);
        mImageIv.setImageBitmap(bitmap);
    }
}