package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Mainhome2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainhome2);
    }

    public void page_login(View view) {
        Intent intent = new Intent(Mainhome2.this,page_login.class);
        startActivity(intent );

    }

    public void page_register(View view) {
        Intent intent = new Intent(Mainhome2.this,page_register.class);
        startActivity(intent );

    }
}