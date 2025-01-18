package com.example.createacc

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
    private var btnLogin: MaterialTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.loginText)
        btnLogin?.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }, 1000) // Adds a 1-second delay before starting the SignInActivity
        }
    }
}
