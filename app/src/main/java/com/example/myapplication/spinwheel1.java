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
    int totalRotation;
    boolean isSpinning;
    long startTime;
    long duration; // Total duration of spin in milliseconds

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
        totalRotation = (random.nextInt(20) + 10) * 67; // Random total rotation between 360 and 1080 degrees
        duration = (random.nextInt(3) + 2) * 1000; // Random duration between 2 and 4 seconds
        isSpinning = true;
        startTime = System.currentTimeMillis();

        runnable = new Runnable() {
            @Override
            public void run() {
                if (isSpinning) {
                    long currentTime = System.currentTimeMillis();
                    long elapsedTime = currentTime - startTime;
                    float fraction = (float) elapsedTime / duration;
                    float easedFraction = cubicEaseOut(fraction);

                    float rotation = totalRotation * easedFraction;
                    ivWheel.setRotation(rotation);

                    if (fraction >= 1.0) {
                        isSpinning = false;
                        btnSpin.setEnabled(true);

                        // Intent to move to the next activity
                        Intent intent = new Intent(spinwheel1.this, scan2.class);
                        startActivity(intent);
                        finish(); // Optional: Close the current activity

                    } else {
                        handler.postDelayed(this, 16); // Approximately 60 FPS
                    }
                }
            }
        };

        handler.post(runnable);
    }

    // Cubic easing out function
    private float cubicEaseOut(float t) {
        return 1 - (float)Math.pow(1 - t, 3);
    }
}
