package com.example.createacc

import android.content.Context
import android.content.SharedPreferences
import android.util.Patterns

class CredentialsManager(context: Context) {

    private val sPreferences: SharedPreferences = context.getSharedPreferences("UserCredentials", Context.MODE_PRIVATE)
    private val map: MutableMap<String, String> = mutableMapOf()

    init {
        sPreferences.all.forEach { (key, value) ->
            if (value is String) {
                map[key] = value
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun register(email: String, password: String): String {
        val normalizedEmail = email.trim().lowercase()

        if (!isValidEmail(email)) {
            return "Invalid email format."
        }
        if (!isValidPassword(password)) {
            return "Password cannot be empty."
        }

        if (map.containsKey(normalizedEmail)) {
            return "Email already exists."
        } else {
            map[normalizedEmail] = password
            val editor = sPreferences.edit()
            editor.putString(normalizedEmail, password)
            editor.apply()
            return "Registration successful."
        }
    }

    fun validateCredentials(email: String, password: String): Boolean {
        val normalizedEmail = email.trim().lowercase()
        return map[normalizedEmail] == password
    }
}
