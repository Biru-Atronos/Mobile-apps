package com.example.createacc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class RegisteredActivity : AppCompatActivity() {
    private lateinit var credentialsManager: CredentialsManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        credentialsManager = CredentialsManager(this)


        val nameInput = findViewById<TextInputEditText>(R.id.fullNameInput)
        val email = findViewById<TextInputEditText>(R.id.emailInput)
        val phone = findViewById<TextInputEditText>(R.id.phoneInput)
        val password = findViewById<TextInputEditText>(R.id.passwordInput)
        val next = findViewById<MaterialButton>(R.id.nextButton)
        val loginText = findViewById<MaterialTextView>(R.id.loginText)

        next.setOnClickListener {
            val fullName = nameInput.text.toString().trim()
            val email = email.text.toString().trim()
            val phone = phone.text.toString().trim()
            val password = password.text.toString().trim()

            if (fullName.isBlank() || email.isBlank() || phone.isBlank() || password.isBlank()) {
                showToast("All fields must be filled out.")
                return@setOnClickListener
            }

            val result = credentialsManager.register(email, password)
            showToast(result)

            if (result == "Registration successful.") {
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }
        }

        loginText.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
