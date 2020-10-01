package com.example.uber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnAdmin = findViewById(R.id.btn_admin);
        Button btnUser = findViewById(R.id.btn_user);
        btnAdmin.setOnClickListener(this);
        btnUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_admin){
            Intent intent = new Intent(this,AdminActivity.class);
            startActivity(intent);

        }
        if (v.getId() == R.id.btn_user){
            Intent intent = new Intent(this,UserActivity.class);
            startActivity(intent);

        }
    }
}