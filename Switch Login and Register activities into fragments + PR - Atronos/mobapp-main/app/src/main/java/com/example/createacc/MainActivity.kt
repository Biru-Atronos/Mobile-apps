package com.example.createacc

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.createacc.fragments.FragmentA
import com.example.createacc.fragments.FragmentB
import com.example.createacc.fragments.LoginFragment

class MainActivity : AppCompatActivity() {
    private var isFragmentAVisible = true
    private lateinit var credentialsManager: CredentialsManager
    private lateinit var switchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        credentialsManager = CredentialsManager(this)
        switchButton = findViewById(R.id.btn_switch_fragment)

        if (savedInstanceState == null) {
            showFragment(LoginFragment(credentialsManager))
        }

        switchButton.setOnClickListener {
            if (isFragmentAVisible) {
                showFragment(FragmentB())
            } else {
                showFragment(FragmentA())
            }
            isFragmentAVisible = !isFragmentAVisible
        }
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        adjustSwitchButtonVisibility(fragment)
    }

    private fun adjustSwitchButtonVisibility(fragment: Fragment) {
        switchButton.visibility = if (fragment is FragmentA || fragment is FragmentB) {
            Button.VISIBLE
        } else {
            Button.GONE
        }
    }
}
