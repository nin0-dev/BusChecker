package com.nin0dev.buschecker

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.google.android.material.color.DynamicColors
import com.google.android.material.navigation.NavigationBarView

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

        navBar()
    }
    fun navBar()
    {
        val navbar = findViewById<NavigationBarView>(R.id.bottom_navigation)
        navbar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home_menu -> {
                    navbar.menu.getItem(0).icon = AppCompatResources.getDrawable(applicationContext, R.drawable.ic_baseline_home_24)
                    navbar.menu.getItem(1).icon = AppCompatResources.getDrawable(applicationContext, R.drawable.ic_outline_directions_bus_24)
                    navbar.menu.getItem(2).icon = AppCompatResources.getDrawable(applicationContext, R.drawable.ic_outline_settings_24)
                    true
                }
                R.id.bus_stops_menu -> {
                    navbar.menu.getItem(0).icon = AppCompatResources.getDrawable(applicationContext, R.drawable.ic_outline_home_24)
                    navbar.menu.getItem(1).icon = AppCompatResources.getDrawable(applicationContext, R.drawable.ic_baseline_directions_bus_24)
                    navbar.menu.getItem(2).icon = AppCompatResources.getDrawable(applicationContext, R.drawable.ic_outline_settings_24)
                    true
                }
                R.id.settings_menu -> {
                    navbar.menu.getItem(0).icon = AppCompatResources.getDrawable(applicationContext, R.drawable.ic_outline_home_24)
                    navbar.menu.getItem(1).icon = AppCompatResources.getDrawable(applicationContext, R.drawable.ic_outline_directions_bus_24)
                    navbar.menu.getItem(2).icon = AppCompatResources.getDrawable(applicationContext, R.drawable.ic_baseline_settings_24)
                    true
                }
                else -> false
            }
        }
    }
}