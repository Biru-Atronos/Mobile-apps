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
import com.google.android.material.textview.MaterialTextView

class RegisterFragment(private val credentialsManager: CredentialsManager) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.activity_main, container, false)

        view.apply {
            val nameInput = findViewById<TextInputEditText>(R.id.fullNameInput)
            val emailInput = findViewById<TextInputEditText>(R.id.emailInput)
            val phoneInput = findViewById<TextInputEditText>(R.id.phoneInput)
            val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)
            val nextButton = findViewById<MaterialButton>(R.id.nextButton)
            val loginText = findViewById<MaterialTextView>(R.id.loginText)

            nextButton.setOnClickListener {
                val fullName = nameInput.text.toString().trim()
                val email = emailInput.text.toString().trim()
                val phone = phoneInput.text.toString().trim()
                val password = passwordInput.text.toString().trim()

                if (fullName.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty()) {
                    val result = credentialsManager.register(email, password)
                    Toast.makeText(requireContext(), result, Toast.LENGTH_SHORT).show()

                    if (result == "Registration successful.") {
                        (activity as? MainActivity)?.showFragment(
                            LoginFragment(credentialsManager)
                        )
                    }
                } else {
                    Toast.makeText(requireContext(), "All fields must be filled out.", Toast.LENGTH_SHORT).show()
                }
            }

            loginText.setOnClickListener {
                (activity as? MainActivity)?.showFragment(LoginFragment(credentialsManager))
            }
        }

        return view
    }
}
