package com.nin0dev.buschecker

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.color.DynamicColors

class AddBusStopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyIfAvailable(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_bus_stop)
        supportActionBar?.setTitle("Add bus stop")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
    }
    override fun onCreateOptionsMenu(menu: Menu) : Boolean {
        menuInflater.inflate(R.menu.appbar_addbus, menu)
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
        return true
    }
    override fun onBackPressed() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
        super.onBackPressed()
    }
}