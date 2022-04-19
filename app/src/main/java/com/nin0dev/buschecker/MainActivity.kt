package com.nin0dev.buschecker

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.color.DynamicColors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyIfAvailable(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sp = getSharedPreferences("e", Context.MODE_PRIVATE)
        if(ContextCompat.checkSelfPermission(this, "android.permission.SEND_SMS") == PackageManager.PERMISSION_DENIED)
        {
            val i = Intent(this, SetupActivity::class.java)
            startActivity(i)
        }
    }
}