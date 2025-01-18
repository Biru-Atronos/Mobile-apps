package com.example.createacc

import android.util.Patterns

class CredentialsManager {

    private val accounts = mutableMapOf<String, String>()

    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty()
    }
    fun register(email: String, password: String): String {
        val normalizedEmail = email.trim().lowercase()

        if (accounts.containsKey(normalizedEmail)) {
            return "Email is already taken. Please choose a different email."
        }

        if (!isValidEmail(email)) {
            return "Please enter a valid email address."
        }

        if (!isValidPassword(password)) {
            return "Password cannot be empty."
        }
        accounts[normalizedEmail] = password
        return "Registration successful!"
    }

    fun isEmailTaken(email: String): Boolean {
        return accounts.containsKey(email.trim().lowercase())
    }
    fun login(email: String, password: String): Boolean {
        val normalizedEmail = email.trim().lowercase()
        return accounts[normalizedEmail] == password
    }
}
