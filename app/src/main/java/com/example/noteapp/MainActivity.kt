package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.os.Handler
import android.widget.Button
import android.widget.LinearLayout
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val delayMillis: Long = 5000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({

            startActivity(Intent(this@MainActivity,Note::class.java))

            finish()
        }, delayMillis)



    }

}