package com.nin0dev.buschecker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Run setup activity, thanks!
        val i = Intent(this, SetupActivity::class.java)
        startActivity(i)
        finish()
    }
}