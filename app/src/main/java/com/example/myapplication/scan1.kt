package com.example.myapplication

import android.content.Intent
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.google.firebase.database.FirebaseDatabase

private const val CAMERA_REQUEST_CODE = 101

class scan1 : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner
    private lateinit var scannerView: CodeScannerView
    private lateinit var tv_textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan1)

        val imageView: ImageView = findViewById(R.id.img_textView)
        imageView.visibility = View.INVISIBLE

        val menu = findViewById<Button>(R.id.vector)
        menu.setOnClickListener {
            Intent(this, main_menu::class.java).also{
                startActivity(it)
            }
        }

        val btnlogin = findViewById<Button>(R.id.btnlogin)
        btnlogin.setOnClickListener {
            Intent(this, scan2::class.java).also{
                startActivity(it)
            }
        }


        tv_textView = findViewById(R.id.tv_textView)

        setupPermission()
        scannerView = findViewById(R.id.scanner_view)
        codeScanner()
    }

    private fun codeScanner() {
        val imageView: ImageView = findViewById(R.id.img_textView)
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
//                    tv_textView.text = it.text
                    imageView.visibility = View.VISIBLE

                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("main", "camera error, ${it.message}")
                }
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
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
                    // Izin kamera diberikan, lakukan tindakan yang sesuai di sini
                    codeScanner.startPreview()
                } else {
                    // Izin kamera tidak diberikan, tampilkan pesan kepada pengguna
                    Toast.makeText(this, "You need camera permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
