package com.nin0dev.buschecker

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.color.DynamicColors

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyIfAvailable(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        buttons()
        supportActionBar?.setTitle(getString(R.string.about))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        val image = findViewById<ImageView>(R.id.busCheckerImageAbout)
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
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        return true
    }
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        super.onBackPressed()
    }
    fun buttons()
    {
        val supportButton = findViewById<Button>(R.id.supportButton)
        val contactButton = findViewById<Button>(R.id.contactButton)
        val privacyButton = findViewById<Button>(R.id.privacyButton)
        val licenseButton = findViewById<Button>(R.id.licenseButton)
        val sourceCodeButton = findViewById<Button>(R.id.sourceCodeButton)

        supportButton.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/+UoN55-vB0MxjNzlh"))
            startActivity(myIntent)
        }
        contactButton.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:nin0dev.apps+buschecker@gmail.com"))
            startActivity(myIntent)
        }
        privacyButton.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/nin0-dev/BusChecker/blob/master/PRIVACY.md"))
            startActivity(myIntent)
        }
        licenseButton.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/nin0-dev/BusChecker/blob/master/LICENSE"))
            startActivity(myIntent)
        }
        sourceCodeButton.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/nin0-dev/BusChecker"))
            startActivity(myIntent)
        }
    }
}