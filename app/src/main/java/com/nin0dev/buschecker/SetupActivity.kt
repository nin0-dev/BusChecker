package com.nin0dev.buschecker

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.color.DynamicColors
import java.security.AccessController.getContext
import java.util.jar.Manifest

class SetupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyIfAvailable(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)
        buttons()
        startImageTint()
    }
    fun startImageTint()
    {
        val image = findViewById<ImageView>(R.id.busCheckerImage)
        if(Build.VERSION.SDK_INT >= 31)
        {
            // User is on Android 12+, use dynamic colors
            val nightModeFlags: Int = applicationContext.resources.getConfiguration().uiMode and
                    Configuration.UI_MODE_NIGHT_MASK
            when (nightModeFlags) {
                Configuration.UI_MODE_NIGHT_YES ->
                {
                    // Dark mode is on, set A1-700
                    image.setColorFilter(ContextCompat.getColor(applicationContext, com.google.android.material.R.color.material_dynamic_primary70), android.graphics.PorterDuff.Mode.SRC_IN)
                }
                Configuration.UI_MODE_NIGHT_NO ->
                {
                    // Dark mode is off, set A1-200
                    image.setColorFilter(ContextCompat.getColor(applicationContext, com.google.android.material.R.color.material_dynamic_primary40), android.graphics.PorterDuff.Mode.SRC_IN)
                }
                Configuration.UI_MODE_NIGHT_UNDEFINED ->
                {
                    // Assume light theme, set A1-200
                    image.setColorFilter(ContextCompat.getColor(applicationContext, com.google.android.material.R.color.material_dynamic_primary40), android.graphics.PorterDuff.Mode.SRC_IN)
                }
            }
        }
        else
        {
            // User is NOT on Android 12, use Material colors
            val nightModeFlags: Int = applicationContext.resources.getConfiguration().uiMode and
                    Configuration.UI_MODE_NIGHT_MASK
            when (nightModeFlags) {
                Configuration.UI_MODE_NIGHT_YES ->
                {
                    // Dark mode is on, set Purple 200
                    image.setColorFilter(ContextCompat.getColor(applicationContext, R.color.purple_200), android.graphics.PorterDuff.Mode.SRC_IN)
                }
                Configuration.UI_MODE_NIGHT_NO ->
                {
                    // Dark mode is off, set Purple 500
                    image.setColorFilter(ContextCompat.getColor(applicationContext, R.color.purple_500), android.graphics.PorterDuff.Mode.SRC_IN)
                }
                Configuration.UI_MODE_NIGHT_UNDEFINED ->
                {
                    // Assume light theme, set Purple 500
                    image.setColorFilter(ContextCompat.getColor(applicationContext, R.color.purple_500), android.graphics.PorterDuff.Mode.SRC_IN)
                }
            }
        }
    }
    fun switchScreens(hide: View, show: View, title: String, subtitle: String = "")
    {
        hide.visibility = View.GONE
        show.visibility = View.VISIBLE
        supportActionBar?.setTitle(title)
        supportActionBar?.setSubtitle(subtitle)
    }
    fun updateActionBarText(title: String, subtitle: String = "")
    {
        supportActionBar?.setTitle(title)
        supportActionBar?.setSubtitle(subtitle)
    }
    fun buttons()
    {
        val notificationsButton = findViewById<Button>(R.id.grantNotificationsButton)
        notificationsButton.setOnClickListener {
            ActivityCompat.requestPermissions(this,
                arrayOf("android.permission.POST_NOTIFICATIONS"), 1
            )

        }
        val smsButton = findViewById<Button>(R.id.grantSMSButton)
        smsButton.setOnClickListener {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.SEND_SMS), 1
            )

        }
    }
    fun components()
    {

    }
}