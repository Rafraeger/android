package com.example.myapplication

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import android.widget.ImageView
import android.view.View
import android.content.SharedPreferences

private const val CAMERA_REQUEST_CODE = 101
private const val SCAN_DELAY = 3000L // Delay in milliseconds (3 seconds)

class scan1 : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner
    private lateinit var scannerView: CodeScannerView
    private lateinit var tv_textView: TextView
    private val scannedCodes = mutableListOf<String>()
    private var isScanEnabled = true // Flag to control barcode scanning
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan1)

        val imageView: ImageView = findViewById(R.id.img_textView)
        imageView.visibility = View.INVISIBLE

        val menu = findViewById<Button>(R.id.vector)
        menu.setOnClickListener {
            Intent(this, main_menu::class.java).also {
                startActivity(it)
            }
        }

        val btnlogin = findViewById<Button>(R.id.btnlogin)
        btnlogin.setOnClickListener {
            Intent(this, main_menu::class.java).also {
                startActivity(it)
            }
        }

        tv_textView = findViewById(R.id.btnlogin)

        setupPermission()
        scannerView = findViewById(R.id.scanner_view)
        codeScanner()
    }

    private fun codeScanner() {
        val imageView: ImageView = findViewById(R.id.img_textView)
        val imageViewWarning: ImageView = findViewById(R.id.img_textView_w)
        imageViewWarning.visibility = View.INVISIBLE

        codeScanner = CodeScanner(this, scannerView)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    val scannedCode = it.text
                    if (isScanEnabled) {
                        if (scannedCodes.contains(scannedCode)) {
                            imageView.visibility = View.INVISIBLE
                            imageViewWarning.visibility = View.VISIBLE
                            tv_textView.text = "submit"
                        } else {
                            scannedCodes.add(scannedCode)
                            imageView.visibility = View.VISIBLE
                            imageViewWarning.visibility = View.INVISIBLE
                            tv_textView.text = "save"
                            // Update the score
                            updateScore()

                            delayScan()
                        }
                    }
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("main", "camera error, ${it.message}")
                }
            }
        }

        scannerView.setOnClickListener {
            if (isScanEnabled) {
                codeScanner.startPreview()
            }
        }
    }

    private fun updateScore() {
        // Get the current score from SharedPreferences
        val prefs = getSharedPreferences("myPrefs", MODE_PRIVATE)
        var score = prefs.getInt("score", 30)

        // Add 100 to the score
        score += 100

        // Save the updated score back to SharedPreferences
        with(prefs.edit()) {
            putInt("score", score)
            apply()
        }
    }

    override fun onResume() {
        super.onResume()
        if (isScanEnabled) {
            codeScanner.startPreview()
        }
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermission() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    codeScanner.startPreview()
                } else {
                    Toast.makeText(this, "You need camera permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Method untuk menunda pemindaian barcode selama SCAN_DELAY
    private fun delayScan() {
        isScanEnabled = false
        handler.postDelayed({
            isScanEnabled = true
            codeScanner.startPreview() // Restart the preview after delay
        }, SCAN_DELAY)
    }
}
