package com.cuty.constraints

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addAnimationOperations()
        setNextButton()
    }
    private fun addAnimationOperations() {
        val constraintLayoutRoot = findViewById<ConstraintLayout>(R.id.root)
        var set = false
        val constraint1 = ConstraintSet()
        constraint1.clone( constraintLayoutRoot)
        val constraint2 = ConstraintSet()
        constraint2.clone(this, R.layout.activity_image_alt)

        findViewById<ImageView>(R.id.imageView).setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition(constraintLayoutRoot)
                val constraint = if(set) constraint1 else constraint2
                constraint.applyTo(constraintLayoutRoot)
                set = !set
            }
        }

    }
    private fun setNextButton(){
        val nextButton = findViewById<Button>(R.id.bu_next)
        nextButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}