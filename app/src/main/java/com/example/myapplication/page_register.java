package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class page_register extends AppCompatActivity {

    EditText konfirmasi,password;
    boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_register);

        password = findViewById(R.id.rectangle_password);
        konfirmasi = findViewById(R.id.rectangle_konfirmasi);

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= password.getRight() - password.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = password.getSelectionEnd();
                        if (passwordVisible) {
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.vector_ek1, 0, R.drawable.ic_visibility_off, 0);
                        } else {
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.vector_ek1, 0, R.drawable.ic_visibility_on, 0);
                        }
                        password.setSelection(selection);
                        return true;

                    }
                }

                return false;
            }
        });


        konfirmasi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= konfirmasi.getRight() - konfirmasi.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = konfirmasi.getSelectionEnd();
                        if (passwordVisible) {
                            konfirmasi.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                            konfirmasi.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.vector_ek1, 0, R.drawable.ic_visibility_off, 0);
                        } else {
                            konfirmasi.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                            konfirmasi.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.vector_ek1, 0, R.drawable.ic_visibility_on, 0);
                        }
                        konfirmasi.setSelection(selection);
                        return true;

                    }
                }

                return false;
            }
        });

    }

    public void login(View view) {
        Intent intent = new Intent(page_register.this,Mainhome2.class);
        startActivity(intent );

    }
    public void register(View view) {
        Intent intent = new Intent(page_register.this,page_login.class);
        startActivity(intent );

    }
}