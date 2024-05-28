package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class redeemv2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeemv2);
    }
    public void reedemv1(View view) {
        Intent intent = new Intent(redeemv2.this,redeemv1.class);
        startActivity(intent );

    }
}