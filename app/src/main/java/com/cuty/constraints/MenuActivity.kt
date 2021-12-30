package com.cuty.constraints

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.ImageButton
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager

class MenuActivity : AppCompatActivity() {
    lateinit var root : ConstraintLayout
    lateinit var askSize : Button
    lateinit var close : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        root = findViewById(R.id.menu_root)
        askSize = findViewById(R.id.askSize)
        close = findViewById(R.id.close)
        setupAnimations()
    }
    private fun setupAnimations() {
        askSize.setOnClickListener {
            updateConstraints(R.layout.activity_menu_alt)
            askSize.setText("ADD TO CART - 1234 INR")
        }

        close.setOnClickListener {
            updateConstraints(R.layout.activity_menu)
            askSize.setText("SELECT SIZE")
        }
    }


    fun updateConstraints(@LayoutRes id: Int) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this, id)
        newConstraintSet.applyTo(root)
        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator()
        TransitionManager.beginDelayedTransition(root, transition)
    }
}