package com.example.createacc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.createacc.fragments.FragmentA
import com.example.createacc.fragments.LoginFragment

class MainActivity : AppCompatActivity() {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        credentialsManager = CredentialsManager(this)

        if (savedInstanceState == null) {
            showFragment(LoginFragment(credentialsManager))
        }
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
