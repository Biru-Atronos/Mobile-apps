package com.example.createacc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.createacc.CredentialsManager
import com.example.createacc.MainActivity
import com.example.createacc.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView

class LoginFragment(private val credentialsManager: CredentialsManager) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.activity_sigin, container, false)

        view.apply {
            val emailInputLayout = findViewById<TextInputLayout>(R.id.tilEmail)
            val passwordInputLayout = findViewById<TextInputLayout>(R.id.tilPassword)
            val emailEditText = findViewById<TextInputEditText>(R.id.emailInput)
            val passwordEditText = findViewById<TextInputEditText>(R.id.passwordInput)
            val nextButton = findViewById<MaterialButton>(R.id.nextButton)
            val registerNowText = findViewById<MaterialTextView>(R.id.loginText)

            nextButton.setOnClickListener {
                val email = emailEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                if (validateCredentials(email, password, emailInputLayout, passwordInputLayout)) {
                    if (credentialsManager.validateCredentials(email, password)) {
                        Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show()
                        (activity as? MainActivity)?.showFragment(RecipeFragment())
                    } else {
                        showError("Invalid email or password. Please try again.")
                    }
                }
            }

            registerNowText.setOnClickListener {
                (activity as? MainActivity)?.showFragment(RegisterFragment(credentialsManager))
            }
        }

        return view
    }

    private fun validateCredentials(
        email: String,
        password: String,
        emailInputLayout: TextInputLayout,
        passwordInputLayout: TextInputLayout
    ): Boolean {
        var isValid = true
        if (!credentialsManager.isValidEmail(email)) {
            emailInputLayout.error = "Invalid email format"
            isValid = false
        } else {
            emailInputLayout.error = null
        }

        if (password.isEmpty()) {
            passwordInputLayout.error = "Password cannot be empty"
            isValid = false
        } else {
            passwordInputLayout.error = null
        }
        return isValid
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
