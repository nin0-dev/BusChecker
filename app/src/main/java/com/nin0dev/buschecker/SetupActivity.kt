package com.nin0dev.buschecker

import android.content.pm.PackageManager.PERMISSION_DENIED
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.view.View.GONE
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import com.google.android.material.color.DynamicColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SetupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyIfAvailable(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)
        buttons()
        components()
        startImageTint()
        updateActionBarText("Setup")
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
        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            switchScreens(findViewById(R.id.start), findViewById(R.id.disclaimer), "Warning")
        }
        val notResponsibleButton = findViewById<Button>(R.id.notResponsibleButton)
        notResponsibleButton.setOnClickListener {
            switchScreens(findViewById(R.id.disclaimer), findViewById(R.id.permissions), "Permissions")
        }
        val notificationsButton = findViewById<Button>(R.id.grantNotificationsButton)
        notificationsButton.setOnClickListener {
            ActivityCompat.requestPermissions(this,
                arrayOf("android.permission.POST_NOTIFICATIONS"), 1
            )
            if(ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") == PERMISSION_GRANTED)
            {
                // Permission granted
                notificationsButton.isEnabled = false
                notificationsButton.text = "Granted"
            }
            if(ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") == PERMISSION_DENIED)
            {
                // Permission denied
                notificationsButton.text = "Grant"
                //return@setOnClickListener
            }
        }
        val smsButton = findViewById<Button>(R.id.grantSMSButton)
        smsButton.setOnClickListener {
            ActivityCompat.requestPermissions(this,
                arrayOf("android.permission.SEND_SMS"), 1
            )
            if(ContextCompat.checkSelfPermission(this, "android.permission.SEND_SMS") == PERMISSION_GRANTED)
            {
                // Permission granted
                smsButton.isEnabled = false
                smsButton.text = "Granted"
            }
            if(ContextCompat.checkSelfPermission(this, "android.permission.SEND_SMS") == PERMISSION_DENIED)
            {
                smsButton.text = "Grant"
            }
        }
        val continueButton = findViewById<Button>(R.id.continueButton)
        continueButton.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, "android.permission.SEND_SMS") == PERMISSION_GRANTED)
            {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Warning")
                    .setMessage("You will see a popup saying that BusChecker sending a SMS to 52736 may cause charges. Click on \"Remember my choice\" and on \"Always Allow\".")
                    .setPositiveButton("OK") { dialog, which ->
                        val smgr: SmsManager = SmsManager.getDefault()
                        smgr.sendTextMessage(
                            "52786",
                            null,
                            "E",
                            null,
                            null
                        )
                        MaterialAlertDialogBuilder(this)
                            .setTitle("Info")
                            .setMessage("You should receive soon a SMS saying that \"your language is now English\". Click on it, and block the number. If you don't know how to do it, search on Google \"Block SMS conversation [device model]\".")
                            .setPositiveButton("OK") { dialog, which ->

                            }
                            .show()
                    }
                    .show()

            }
            else
            {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Permissions not granted")
                    .setMessage("You must grant SMS permission to BusChecker to continue.")
                    .setPositiveButton("OK") { dialog, which ->

                    }
                    .show()
            }
        }
    }

    fun components()
    {
        if(Build.VERSION.PREVIEW_SDK_INT == 0)
        {
            findViewById<MaterialCardView>(R.id.notificationsCard).visibility = GONE
        }
    }
}