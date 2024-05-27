package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Random;

public class spinwheel1 extends AppCompatActivity {

    Button btnSpin;
    ImageView ivWheel;
    Handler handler;
    Runnable runnable;
    int rotationIncrement;
    int totalRotation;
    boolean isSpinning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinwheel1);

        btnSpin = findViewById(R.id.rectangle_11);
        ivWheel = findViewById(R.id.wheel);
        handler = new Handler();

        btnSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSpin.setEnabled(false);
                startSpin();
            }
        });
    }

    private void startSpin() {
        Random random = new Random();
        totalRotation = random.nextInt(20) + 10;
        totalRotation = totalRotation * 36;

        rotationIncrement = 20;  // Initial speed
        isSpinning = true;

        runnable = new Runnable() {
            @Override
            public void run() {
                if (isSpinning) {
                    float rotation = ivWheel.getRotation() + rotationIncrement;
                    ivWheel.setRotation(rotation);

                    totalRotation -= rotationIncrement;
                    if (totalRotation <= 0) {
                        isSpinning = false;
                        rotationIncrement = 0;
                        btnSpin.setEnabled(true);

                        // Intent to move to the next activity
                        Intent intent = new Intent(spinwheel1.this, selamat_undian.class);
                        startActivity(intent);
                        finish(); // Optional: Close the current activity

                        return;
                    }

                    if (totalRotation < 360) { // Slow down when close to stop
                        rotationIncrement = Math.max(2, rotationIncrement - 1);
                    }

                    handler.postDelayed(this, 20); // Delay for smooth animation
                }
            }
        };

        handler.post(runnable);
    }
}
