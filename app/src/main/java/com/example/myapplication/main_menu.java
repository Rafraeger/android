package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import androidx.core.view.GravityCompat;




public class main_menu extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Button groupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        groupButton = findViewById(R.id.group_869);

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });

    }

    public void scan(View view) {
        Intent intent = new Intent(main_menu.this,scan1.class);
        startActivity(intent );

    }

    public void reedemv(View view) {
        Intent intent = new Intent(main_menu.this,redeemv2.class);
        startActivity(intent );

    }

    public void wheel(View view) {
        Intent intent = new Intent(main_menu.this,spinwheel1.class);
        startActivity(intent );

    }

    public void scan1(View view) {
        Intent intent = new Intent(main_menu.this,scan1.class);
        startActivity(intent );

    }
}