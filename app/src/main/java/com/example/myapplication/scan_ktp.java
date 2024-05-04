package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class scan_ktp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_ktp);
    }

    public void scan_tutup_botol(View view) {
        Intent intent = new Intent(scan_ktp.this,scan_tutup_botol.class);
        startActivity(intent );

    }

    public void selamat(View view) {
        Intent intent = new Intent(scan_ktp.this,selamat_klaim_tutup_botol.class);
        startActivity(intent );

    }
}