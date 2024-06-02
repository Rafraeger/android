package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class selamat_undian extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selamat_undian);
    }

    public void wheel1(View view) {
        Intent intent = new Intent(selamat_undian.this, spinwheel1.class);
        startActivity(intent);
    }
}