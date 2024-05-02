package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class scan_tutup_botol extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_tutup_botol);
    }

    public void main_menu(View view) {
        Intent intent = new Intent(scan_tutup_botol.this,main_menu.class);
        startActivity(intent );

    }
}