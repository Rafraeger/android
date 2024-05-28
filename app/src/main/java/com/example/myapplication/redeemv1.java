package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class redeemv1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeemv1);

    }

    public void main_menu(View view) {
        Intent intent = new Intent(redeemv1.this,main_menu.class);
        startActivity(intent );

    }
    public void redeemv2(View view) {
        Intent intent = new Intent(redeemv1.this,redeemv2.class);
        startActivity(intent );

    }
}