package com.example.createacc

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity

class Slides : AppCompatActivity() {
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var nextButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slides)

        viewFlipper = findViewById(R.id.viewFlipper)
        nextButton = findViewById(R.id.btnGo)

        val inflater = LayoutInflater.from(this)
        val slide1 = inflater.inflate(R.layout.slide_1, viewFlipper, false)
        val slide2 = inflater.inflate(R.layout.slide_2, viewFlipper, false)
        val slide3 = inflater.inflate(R.layout.slide_3, viewFlipper, false)

        viewFlipper.addView(slide1)
        viewFlipper.addView(slide2)
        viewFlipper.addView(slide3)

        nextButton.setOnClickListener {
            if (viewFlipper.displayedChild == viewFlipper.childCount - 1) {
                navigateToRegisterFragment()
            } else {
                viewFlipper.showNext()
            }
        }
    }

    private fun navigateToRegisterFragment() {
        val mainActivityIntent = intent.setClass(this, MainActivity::class.java)
        mainActivityIntent.putExtra("navigateTo", "RegisterFragment")
        startActivity(mainActivityIntent)
        finish()
    }
}
