package com.cuty.constraints

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager

class SecondActivity : AppCompatActivity() {
    private var selectedView : ImageView? = null
    private lateinit var root : ConstraintLayout
    private lateinit var javaImg : ImageView
    private lateinit var kotlinImg : ImageView
    private lateinit var bu_menu : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Toast.makeText(this,"Second Activity",Toast.LENGTH_LONG).show()
        root = findViewById(R.id.second_root)
        javaImg = findViewById<ImageView>(R.id.javaImg)
        kotlinImg = findViewById<ImageView>(R.id.kotlinImg)
        bu_menu = findViewById(R.id.bu_to_menu)
        setupAnimationn()
        setMenuButton()
    }
    private fun setupAnimationn(){
        selectedView = null

        root.setOnClickListener {
            toDefault()
        }
        javaImg.setOnClickListener {
            if (selectedView != javaImg){
                updateConstraints(R.layout.activity_carousel_java)
                selectedView = javaImg
            }else
                toDefault()
        }
        kotlinImg.setOnClickListener {
            if (selectedView != kotlinImg){
                updateConstraints(R.layout.activity_carousel_kotlin)
                selectedView = kotlinImg
            }else
                toDefault()
        }


    }
    private fun toDefault() {
        if (selectedView != null) {
            updateConstraints(R.layout.activity_second)
            selectedView = null
        }
    }
    fun updateConstraints(@LayoutRes id: Int) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this, id)
        newConstraintSet.applyTo(root)
        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator()
        TransitionManager.beginDelayedTransition(root,transition)
    }
    private fun setMenuButton(){
        val nextButton = findViewById<Button>(R.id.bu_to_menu)
        nextButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

}