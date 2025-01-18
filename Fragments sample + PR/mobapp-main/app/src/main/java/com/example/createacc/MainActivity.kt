package com.example.createacc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.createacc.fragments.FragmentA
import com.example.createacc.fragments.FragmentB

class MainActivity : AppCompatActivity() {
    private var isFragmentAVisible = true
    private lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        credentialsManager = CredentialsManager(this)

        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        if (email != null && password != null) {
            if (credentialsManager.validateCredentials(email, password)) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, FragmentA())
                    .commit()
            } else {
                Toast.makeText(this, "Invalid email or password.", Toast.LENGTH_SHORT).show()
                redirectToLogin()
            }
        } else {
            redirectToLogin()
        }

        val switchButton = findViewById<Button>(R.id.btn_switch_fragment)
        switchButton.setOnClickListener {
            if (isFragmentAVisible) {
                replaceFragment(FragmentB())
            } else {
                replaceFragment(FragmentA())
            }
            isFragmentAVisible = !isFragmentAVisible
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun redirectToLogin() {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }
}
